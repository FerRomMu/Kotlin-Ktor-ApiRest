package vsapp.plugins

import org.koin.dsl.module
import org.koin.core.context.startKoin
import vsapp.controllers.MemberController
import vsapp.controllers.PartyController
import vsapp.controllers.UserController
import vsapp.model.dtos.mapping.MemberMapper
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.model.dtos.mapping.UserMapper
import vsapp.repository.mockDb.MockPartyDAO
import vsapp.repository.mockDb.MockUserDAO
import vsapp.repository.mockDb.MockMemberDAO
import vsapp.service.*

fun configureKoin(){
    startKoin {
        modules(koinModule)
    }
}

val koinModule = module {

    //Services
    single<UserService> { UserServiceImpl(MockUserDAO()) }
    single<PartyService> { PartyServiceImpl(MockPartyDAO()) }
    single<MemberService> { MemberServiceImpl(MockMemberDAO()) }

    //Mappers
    single { MemberMapper() }
    single { PartyMapper(get()) }
    single { UserMapper() }

    //Controllers
    single { PartyController(get(), get()) }
    single { UserController(get(), get()) }
    single { MemberController(get(), get()) }

}