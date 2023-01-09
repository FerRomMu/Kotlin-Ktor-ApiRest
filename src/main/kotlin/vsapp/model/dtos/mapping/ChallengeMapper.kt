package vsapp.model.dtos.mapping

import vsapp.model.Category
import vsapp.model.Challenge
import vsapp.model.ChallengeResult
import vsapp.model.Member
import vsapp.model.dtos.*

class ChallengeMapper {

    fun categoriesToDTO(categories: List<Category>): CategoriesDTO {
        TODO("Not yet implemented")
    }

    fun categoryFromDTO(categoryDTO: CategoryDTO): Category {
        TODO("Not yet implemented")
    }

    fun challengeToDTO(challenge: Challenge?): ChallengeDTO? {
        TODO("Not yet implemented")
    }

    fun resultFromDTO(resultDTO: ChallengeResultDTO): ChallengeResult {
        TODO("Not yet implemented")
    }

    fun toPointsDTO(saveResult: List<Member>, partyId: Long): PointsDTO? {
        TODO("Not yet implemented")
    }

}
