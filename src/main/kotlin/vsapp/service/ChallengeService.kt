package vsapp.service

import vsapp.model.Category
import vsapp.model.Challenge

interface ChallengeService {
    fun getCategories(): List<Category>
    fun getChallenge(category: Category, memberId: Long, userId: Long): Challenge?
}
