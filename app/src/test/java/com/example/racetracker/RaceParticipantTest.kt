import com.example.racetracker.ui.RaceParticipant
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1
    )

    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1


        launch { raceParticipant.run() }


        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()

        assertEquals(expectedProgress, raceParticipant.currentProgress, "La progression devrait être de 1 après le premier délai.")
    }

    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {

        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()

        assertEquals(100, raceParticipant.currentProgress, "La progression devrait être de 100 après la fin de la course.")
    }
}
