package com.inspire.common.cookie.config;

import com.inspire.common.cookie.CookieUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for CookieAutoConfiguration")
public class CookieAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CookieAutoConfiguration.class));

    @Test
    @DisplayName("should register CookieUtils and CookieProperties when no other bean exists")
    void shouldRegisterCookieUtilsBean() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(CookieUtils.class);
            assertThat(context).hasSingleBean(CookieProperties.class);
        });
    }

    @Test
    @DisplayName("should not register CookieUtils if already defined")
    void shouldNotRegisterCookieUtilsIfAlreadyDefined() {
        CookieUtils cookieUtils = new CookieUtils(false, "Lax");

        contextRunner
                .withBean(CookieUtils.class, () -> cookieUtils)
                .run(context -> {
                    assertThat(context).hasSingleBean(CookieUtils.class);
                    assertThat(context.getBean(CookieUtils.class)).isSameAs(cookieUtils);
                });
    }

    @Test
    @DisplayName("should bind cookie-related properties")
    void shouldBindCookieProperties() {
        contextRunner
                .withPropertyValues(
                        "cookie.secure=false",
                        "cookie.same-site=Lax"
                )
                .run(context -> {
                    CookieProperties properties = context.getBean(CookieProperties.class);
                    assertThat(properties.getSecure()).isEqualTo(false);
                    assertThat(properties.getSameSite()).isEqualTo("Lax");
                });
    }
}
