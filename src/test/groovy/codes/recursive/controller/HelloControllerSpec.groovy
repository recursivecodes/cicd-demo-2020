package codes.recursive.controller

import codes.recursive.AbstractSpec
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.AutoCleanup
import spock.lang.Shared

import javax.inject.Inject

@MicronautTest
class HelloControllerSpec extends AbstractSpec {

    @Shared @Inject
    EmbeddedServer embeddedServer

    @Shared @AutoCleanup @Inject @Client("/")
    RxHttpClient client

    void "test index"() {
        given:
        HttpResponse response = client.toBlocking().exchange("/hello")

        expect:
        response.status == HttpStatus.OK
    }
}
