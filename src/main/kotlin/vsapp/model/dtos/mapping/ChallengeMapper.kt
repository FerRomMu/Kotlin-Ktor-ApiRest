package vsapp.model.dtos.mapping

import vsapp.model.Category
import vsapp.model.Challenge
import vsapp.model.ChallengeResult
import vsapp.model.Member
import vsapp.model.dtos.*

class ChallengeMapper {

    fun categoriesToDTO(categories: List<Category>): CategoriesDTO {
        return CategoriesDTO(categories.map { category -> categoryToDTO(category) })
    }

    fun categoryToDTO(category: Category): CategoryDTO {
        return CategoryDTO(category.id, category.name, category.description)
    }

    fun categoryFromDTO(categoryDTO: CategoryDTO): Category {
        return Category(categoryDTO.id, categoryDTO.name, categoryDTO.description)
    }

    fun challengeToDTO(challenge: Challenge?): ChallengeDTO? {
        TODO("Not yet implemented")
    }

    fun resultFromDTO(resultDTO: ChallengeResultDTO): ChallengeResult {
        TODO("Not yet implemented")
    }

    fun pointsToDTO(saveResult: List<Member>, partyId: Long): PointsDTO? {
        TODO("Not yet implemented")
    }

}
