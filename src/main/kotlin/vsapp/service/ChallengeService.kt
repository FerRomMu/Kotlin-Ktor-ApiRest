package vsapp.service

import vsapp.model.Category
import vsapp.model.Challenge
import vsapp.model.ChallengeResult
import vsapp.model.Member

interface ChallengeService {
    fun getCategories(): List<Category>
    fun getChallenge(category: Category, memberId: Long, userId: Long): Challenge?
    fun saveResult(result: ChallengeResult, id: Long?): List<Member>
}
