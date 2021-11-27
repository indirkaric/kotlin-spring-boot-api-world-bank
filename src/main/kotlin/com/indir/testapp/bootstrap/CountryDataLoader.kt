package com.indir.testapp.bootstrap

import com.indir.testapp.enum.Indicator
import com.indir.testapp.service.CountryService
import com.indir.testapp.service.LanguageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


@Component
@Order(1)
class CountryDataLoader: CommandLineRunner {

    @Autowired
    lateinit var countryService: CountryService

    @Autowired
    lateinit var languageService: LanguageService

    override fun run(vararg args: String?) {
        countryService.loadData();
        countryService.updateGeneralData(Indicator.GDP)
        countryService.updateGeneralData(Indicator.POPULATION)
        languageService.loadLanguages()
    }
}