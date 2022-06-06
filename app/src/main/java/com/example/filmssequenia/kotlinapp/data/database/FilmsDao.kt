package com.example.filmssequenia.kotlinapp.data.database

import androidx.room.*
import com.example.filmssequenia.kotlinapp.data.database.relations.FilmsGenresCrossRef
import com.example.filmssequenia.kotlinapp.data.database.relations.GenreWithFilms
import com.example.filmssequenia.kotlinapp.data.entities.FilmDb
import com.example.filmssequenia.kotlinapp.data.entities.GenreDb

@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmDb: FilmDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genreDb: GenreDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmsGenreCrossRef(crossRef: FilmsGenresCrossRef)

    @Transaction
    @Query("SELECT * FROM GenreDb WHERE genreName =:genre")
    suspend fun getGenreWithFilms(genre: String): GenreWithFilms

    @Transaction
    @Query("SELECT * FROM GenreDb")
    suspend fun getAllGenres(): List<GenreDb>

    @Transaction
    @Query("SELECT * FROM FilmDb ORDER BY localized_name")
    suspend fun getAllFilms(): List<FilmDb>

    @Transaction
    @Query("SELECT * FROM FilmDb WHERE filmId =:filmId")
    suspend fun getFilmById(filmId: Int): FilmDb

    @Query("DELETE FROM FilmDb")
    suspend fun clearFilmsTable()

    @Query("DELETE FROM GenreDb")
    suspend fun clearGenresTable()

    @Query("DELETE FROM FilmsGenresCrossRef")
    suspend fun clearFilmsGenresCrossRefTable()

    @Transaction
    suspend fun clearAllTables(){
        clearFilmsTable()
        clearGenresTable()
        clearFilmsGenresCrossRefTable()
    }
}