package vsapp.model.dtos.mapping

import vsapp.model.Category
import vsapp.model.Challenge
import vsapp.model.ChallengeResult
import vsapp.model.Member
import vsapp.model.dtos.*

class ChallengeMapper(private val memberMapper: MemberMapper) {

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
        return if(challenge != null) {
            ChallengeDTO(
                challenge.id,
                challenge.title,
                challenge.body,
                challenge.options,
                challenge.points,
                memberMapper.allToSimplifiedDTO(challenge.others)
            )
        } else {
            null
        }
    }

    fun resultFromDTO(resultDTO: ChallengeResultDTO): ChallengeResult {
        return ChallengeResult(
            resultDTO.id,
            resultDTO.partyId,
            resultDTO.option,
            resultDTO.points,
            resultDTO.accepted.map { member -> memberMapper.fromSimplifiedDTO(member, null) },
            resultDTO.rejected.map { member -> memberMapper.fromSimplifiedDTO(member, null) }
        )
    }

    fun pointsToDTO(saveResult: List<Member>, partyId: Long): PointsDTO? {
        TODO("Not yet implemented")
    }

}
