package com.indir.testapp.bootstrap

import com.indir.testapp.entity.User
import com.indir.testapp.helper.Logger
import com.indir.testapp.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


@Component
@Order(2)
class UserDataLoader: CommandLineRunner {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun run(vararg args: String?) {
        if (userRepository.count() <= 0) {
            var user = User(firstName = "John", lastName = "Doe", username = "John.Doe2")
            userRepository.save(user)
            Logger.log.info("User saved ...")
        }
    }
}