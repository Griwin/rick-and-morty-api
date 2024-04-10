package org.mathieu.cleanrmapi.domain.repositories

class EpisodeRepository {
    interface EpisodeRepository {
        suspend fun getEpisodes(): List<Episode>
    }
}