package vsapp.service

import vsapp.model.Category

interface ChallengeService {
    fun getCategories(): List<Category>
}
