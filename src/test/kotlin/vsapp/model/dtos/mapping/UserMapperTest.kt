import io.mockk.mockk
import vsapp.model.User
import vsapp.model.dtos.UserDTO
import vsapp.model.dtos.mapping.PartyMapper
import vsapp.model.dtos.mapping.UserMapper
import kotlin.test.Test
import kotlin.test.assertEquals

class UserMapperTest {
    private val userMapper = UserMapper()

    @Test
    fun `test toDTO method`() {
        //Setup
        val user = User(1, "John", listOf(1, 2, 3), "john@example.com", null)
        val expectedUserDTO = UserDTO(1, "John", listOf(1, 2, 3), "john@example.com", null)

        //Exercise
        val userDTO = userMapper.toDTO(user)

        //Verify
        assertEquals(expectedUserDTO, userDTO)
    }

    @Test
    fun `test fromDTO method`() {
        //Setup
        val userDTO = UserDTO(1, "John", listOf(1, 2, 3), "john@example.com", null)
        val expectedUser = User(1, "John", listOf(1, 2, 3), "john@example.com", null)

        //Exercise
        val user = userMapper.fromDTO(userDTO)

        //Verify
        assertEquals(expectedUser, user)
    }
}