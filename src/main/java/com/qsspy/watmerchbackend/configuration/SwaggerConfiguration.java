package com.qsspy.watmerchbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("WATmerch REST API",
                "Security:\n" +
                        "\n" +
                        "Obecnie dostęp do endpointow (oprocz logowania i rejestracji) maja tylko zarejestrowani uzytkownicy.\n" +
                        "Aby uzyskać dostęp do endointu nalezy w żądaniu zalaczyc naglowek :\n" +
                        "\"Authorization\":\"Basic ('username':'password')\", a tekst w okraglych nawiasach zaszyfrowac algorytmem base64 np:\n" +
                        "\"Authorization\":\"Basic user:user\" --> \"Authorization\":\"Basic dXNlcjp1c2Vy\" i takie cos trzeba zalaczyc\n" +
                        "\n" +
                        "Każde ządanie (oprocz tych bez wymogu autoryzacji) zwraca naglówek:\n" +
                        "\"Cookie\":\"JSESSIONID='jakis token'\"\n" +
                        "Warto go zalaczyc razem z \"Authorization\", bo wtedy spring sprawdza cookie zamiast credentiali i nie pobiera danych z bazy (optymalizacja).\n" +
                        "Token moze sie zmienic w czasie dlatego mozna aktualizowac token po kazdej odpowiedzi serwera.\n" +
                        "Z samym \"Authorization\" tez bedzie dzialac.\n" +
                        "\n" +
                        "W bazie domyslnie znajduja sie 3 uzytkownicy do testowania :\n" +
                        "\n" +
                        "1.\n" +
                        "\n" +
                        "username : user\n" +
                        "password : user\n" +
                        "role : ROLE_USER\n" +
                        "\n" +
                        "2.\n" +
                        "\n" +
                        "username : employee\n" +
                        "password : employee\n" +
                        "role : ROLE_EMPLOYEE\n" +
                        "\n" +
                        "3.\n" +
                        "\n" +
                        "username : admin\n" +
                        "password : admin\n" +
                        "role : ROLE_ADMIN\n" +
                        "======================================",
                "1.0",
                "",
                null,
                "",
                "");
    }
}
