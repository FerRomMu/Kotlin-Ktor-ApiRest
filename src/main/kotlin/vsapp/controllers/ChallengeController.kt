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

    fun getChallenge(request: ChallengeRequestDTO, id: Long): ChallengeDTO? {
        val category = mapper.categoryFromDTO(request.category)
        return mapper.challengeToDTO(service.getChallenge(category, request.memberId, id))
    }

    fun saveResult(resultDTO: ChallengeResultDTO, id: Long?): PointsDTO? {
        val result = mapper.resultFromDTO(resultDTO)
        return mapper.toPointsDTO(service.saveResult(result,id), result.partyId)
    }

}
