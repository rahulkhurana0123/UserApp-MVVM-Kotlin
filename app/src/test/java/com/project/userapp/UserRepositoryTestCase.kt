package com.project.userapp


import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.project.userapp.api.NetworkProvider
import com.project.userapp.api.UserService
import com.project.userapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UserRepositoryTestCase {

    lateinit var networkProvider: NetworkProvider
    @Mock
    lateinit var userService: UserService

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    public var rule = MockitoJUnit.rule()

    @Before
    fun setUp(){
        Dispatchers.setMain(mainThreadSurrogate)
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        networkProvider = NetworkProvider(appContext)
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun testResult() {

        var repository = UserRepository(userService,networkProvider)

        runBlockingTest {
           var response = repository.fetchUserResult()
            Assert.assertEquals(response.isSuccess,true)
        }

    }
    @Test
    fun testNetworkCall_WhenNetworkAvailable() {

        var repository = UserRepository(userService,networkProvider)

        runBlockingTest {
            repository.fetchUserResult()
            verify(userService, times(1)).fetchUser()
        }
    }


    @After
    fun after(){
        Dispatchers.resetMain()
        Mockito.validateMockitoUsage();
    }
}
