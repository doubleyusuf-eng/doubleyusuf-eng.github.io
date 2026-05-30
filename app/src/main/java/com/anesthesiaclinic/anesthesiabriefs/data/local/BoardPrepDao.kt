package com.anesthesiaclinic.anesthesiabriefs.data.local

import androidx.room.*

@Dao
interface BoardPrepDao {

    @Query("SELECT * FROM board_questions WHERE boardType = :boardType")
    fun getQuestionsByBoardType(boardType: String): List<QuestionEntity>

    @Query("SELECT * FROM board_questions WHERE id = :id")
    fun getQuestionById(id: String): QuestionEntity?

    @Query("SELECT * FROM board_questions WHERE isBookmarked = 1")
    fun getBookmarkedQuestions(): List<QuestionEntity>

    @Query("SELECT * FROM board_questions")
    fun getAllQuestions(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(questions: List<QuestionEntity>)

    @Query("DELETE FROM board_questions WHERE boardType = :boardType")
    fun deleteQuestionsByBoardType(boardType: String): Int

    @Query("UPDATE board_questions SET isBookmarked = :isBookmarked WHERE id = :questionId")
    fun updateQuestionBookmark(questionId: String, isBookmarked: Boolean): Int

    @Query("SELECT * FROM board_user_progress WHERE questionId = :questionId")
    fun getUserProgress(questionId: String): UserProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserProgress(progress: UserProgressEntity)

    @Query("SELECT * FROM board_user_progress")
    fun getAllUserProgress(): List<UserProgressEntity>

    @Query("UPDATE board_user_progress SET bookmarked = :bookmarked WHERE questionId = :questionId")
    fun updateProgressBookmark(questionId: String, bookmarked: Boolean): Int

    @Query("SELECT * FROM board_pack_metadata WHERE packId = :packId")
    fun getPackMetadata(packId: String): PackMetadataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPackMetadata(pack: PackMetadataEntity)

    @Query("SELECT * FROM board_pack_metadata")
    fun getAllPackMetadata(): List<PackMetadataEntity>

    @Query("DELETE FROM board_questions")
    fun deleteAllQuestions()

    @Query("DELETE FROM board_pack_metadata")
    fun deleteAllPackMetadata()

    @Query("DELETE FROM board_user_progress")
    fun deleteAllUserProgress()

    @Transaction
    fun importPack(questions: List<QuestionEntity>, metadata: PackMetadataEntity) {
        // Upsert questions
        insertQuestions(questions)
        // Upsert pack metadata
        insertPackMetadata(metadata)
        
        // Sync any existing UserProgress bookmarks with newly imported QuestionEntities
        for (q in questions) {
            val progress = getUserProgress(q.id)
            if (progress != null && progress.bookmarked != q.isBookmarked) {
                updateQuestionBookmark(q.id, progress.bookmarked)
            }
        }
    }
}
