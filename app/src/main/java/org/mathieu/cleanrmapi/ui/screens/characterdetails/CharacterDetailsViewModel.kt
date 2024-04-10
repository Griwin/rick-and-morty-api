package org.mathieu.cleanrmapi.ui.screens.characterdetails

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.cleanrmapi.domain.models.character.Character
import org.mathieu.cleanrmapi.domain.models.episode.Episode
import org.mathieu.cleanrmapi.domain.repositories.CharacterRepository
import org.mathieu.cleanrmapi.ui.core.Destination
import org.mathieu.cleanrmapi.ui.core.ViewModel
import org.mathieu.cleanrmapi.ui.screens.characters.CharactersAction


sealed interface CharacterDetailsAction {
    data class SelectedEpisode(val episode: Episode): CharacterDetailsAction
}

class CharacterDetailsViewModel(application: Application) : ViewModel<CharacterDetailsState>(CharacterDetailsState(), application) {

    private val characterRepository: CharacterRepository by inject()

    fun init(characterId: Int) {
        fetchData(
            source = { characterRepository.getCharacter(id = characterId) }
        ) {

            onSuccess {
                updateState { copy(avatarUrl = it.avatarUrl, name = it.name, episodes = it.episodes, error = null) }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }

    fun handleAction(action: CharacterDetailsAction) {
        when(action) {
            is CharacterDetailsAction.SelectedEpisode -> selectedEpisode(action.episode)
        }
    }

    private fun selectedEpisode(episode: Episode) =
        sendEvent(Destination.EpisodeDetails(episode.id.toString()))
}


data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val error: String? = null,
    val episodes : List<Episode> = emptyList()
)