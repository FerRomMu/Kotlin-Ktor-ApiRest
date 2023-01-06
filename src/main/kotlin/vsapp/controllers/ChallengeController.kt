package vsapp.controllers

import vsapp.model.dtos.*
import vsapp.model.dtos.mapping.ChallengeMapper
import vsapp.service.ChallengeService

class ChallengeController(private val mapper: ChallengeMapper,
                          private val service: ChallengeService
                          ) {

    fun getCategories(): CategoriesDTO {
        return mapper.categoriesToDTO(service.getCategories())
    }

    fun getChallenge(party: PartyDTO, id: Long): ChallengeDTO? {
        TODO()
    }

    fun saveResult(result: ChallengeResultDTO, id: Long?): PointsDTO? {
        TODO()
    }

}
