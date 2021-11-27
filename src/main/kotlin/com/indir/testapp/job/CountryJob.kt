package com.indir.testapp.job

import com.indir.testapp.enum.Indicator
import com.indir.testapp.helper.Logger
import com.indir.testapp.service.CountryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.time.LocalDate

@Configuration
@EnableScheduling
class CountryJob {

    @Autowired
    lateinit var countryService: CountryService

    @Scheduled(cron = "0 0 12 ? JAN *")
    fun updateCountryData() {
        countryService.updateGeneralData(Indicator.POPULATION)
        countryService.updateGeneralData(Indicator.GDP)
        Logger.log.info("Cron has been started {}", LocalDate.now())
    }
}