package de.thkoeln.gm.todolistgdwdemo.users

import jakarta.validation.constraints.Pattern
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.UUID

@DataJpaTest
class UsersRepositoryTests () {

    @Autowired
    lateinit private var  usersRepository: UsersRepository

    @Test
    fun `should add new user to database` (){
        var user: User = User()
        user.email = "viet.nguyen@th-koeln.de"

        val savedUser = usersRepository.save(user)

        assertThat(savedUser).isNotNull
    }
}