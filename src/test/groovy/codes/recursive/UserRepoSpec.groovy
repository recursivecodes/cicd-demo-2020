package codes.recursive

import codes.recursive.model.User
import codes.recursive.repository.UserRepository
import io.micronaut.test.annotation.MicronautTest
import org.testcontainers.spock.Testcontainers

@MicronautTest
@Testcontainers
class UserRepoSpec extends AbstractSpec {
    def "Can create a user"() {
        when:
        UserRepository userRepository = context.getBean(UserRepository)
        def user = new User("Todd", "Sharp", 43, "todd.sharp@oracle.com")
        userRepository.save(user)

        then:
        userRepository.findByFirstName("Todd").size() > 0
    }
}