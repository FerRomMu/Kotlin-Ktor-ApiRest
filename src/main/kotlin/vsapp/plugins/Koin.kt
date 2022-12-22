package vsapp.plugins

import io.ktor.util.logging.*
import org.koin.dsl.module
import vsapp.service.UserService
import vsapp.service.UserServiceImpl
import org.koin.core.context.startKoin
import vsapp.controllers.PartyController
import vsapp.controllers.UserController
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.model.dtos.mapping.UserMapper
import vsapp.service.PartyService
import vsapp.service.PartyServiceImpl

fun configureKoin(){
    startKoin {
        modules(koinModule)
    }
}

val koinModule = module {

    //Services
    single<UserService> { UserServiceImpl() }
    single<PartyService> { PartyServiceImpl() }

    //Mappers
    single { PartyMapper() }
    single { UserMapper(get()) }

    //Controllers
    single { PartyController(get(), get()) }
    single { UserController(get(), get()) }
}