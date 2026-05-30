package com.anesthesiaclinic.anesthesiabriefs.data.local;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0006H\'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\u0006H\'J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0015H\'J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003H\'J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0013H\'J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\'J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u001cH\'J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0\u0003H\'J\b\u0010!\u001a\u00020\fH\'J\b\u0010\"\u001a\u00020\fH\'J\b\u0010#\u001a\u00020\fH\'J\u001e\u0010$\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010%\u001a\u00020\u001cH\u0017\u00a8\u0006&"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDao;", "", "getQuestionsByBoardType", "", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/QuestionEntity;", "boardType", "", "getQuestionById", "id", "getBookmarkedQuestions", "getAllQuestions", "insertQuestions", "", "questions", "deleteQuestionsByBoardType", "", "updateQuestionBookmark", "questionId", "isBookmarked", "", "getUserProgress", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/UserProgressEntity;", "insertUserProgress", "progress", "getAllUserProgress", "updateProgressBookmark", "bookmarked", "getPackMetadata", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/PackMetadataEntity;", "packId", "insertPackMetadata", "pack", "getAllPackMetadata", "deleteAllQuestions", "deleteAllPackMetadata", "deleteAllUserProgress", "importPack", "metadata", "app_debug"})
@androidx.room.Dao()
public abstract interface BoardPrepDao {
    
    @androidx.room.Query(value = "SELECT * FROM board_questions WHERE boardType = :boardType")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity> getQuestionsByBoardType(@org.jetbrains.annotations.NotNull()
    java.lang.String boardType);
    
    @androidx.room.Query(value = "SELECT * FROM board_questions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity getQuestionById(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
    
    @androidx.room.Query(value = "SELECT * FROM board_questions WHERE isBookmarked = 1")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity> getBookmarkedQuestions();
    
    @androidx.room.Query(value = "SELECT * FROM board_questions")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity> getAllQuestions();
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertQuestions(@org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity> questions);
    
    @androidx.room.Query(value = "DELETE FROM board_questions WHERE boardType = :boardType")
    public abstract int deleteQuestionsByBoardType(@org.jetbrains.annotations.NotNull()
    java.lang.String boardType);
    
    @androidx.room.Query(value = "UPDATE board_questions SET isBookmarked = :isBookmarked WHERE id = :questionId")
    public abstract int updateQuestionBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String questionId, boolean isBookmarked);
    
    @androidx.room.Query(value = "SELECT * FROM board_user_progress WHERE questionId = :questionId")
    @org.jetbrains.annotations.Nullable()
    public abstract com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity getUserProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String questionId);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertUserProgress(@org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity progress);
    
    @androidx.room.Query(value = "SELECT * FROM board_user_progress")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity> getAllUserProgress();
    
    @androidx.room.Query(value = "UPDATE board_user_progress SET bookmarked = :bookmarked WHERE questionId = :questionId")
    public abstract int updateProgressBookmark(@org.jetbrains.annotations.NotNull()
    java.lang.String questionId, boolean bookmarked);
    
    @androidx.room.Query(value = "SELECT * FROM board_pack_metadata WHERE packId = :packId")
    @org.jetbrains.annotations.Nullable()
    public abstract com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity getPackMetadata(@org.jetbrains.annotations.NotNull()
    java.lang.String packId);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertPackMetadata(@org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity pack);
    
    @androidx.room.Query(value = "SELECT * FROM board_pack_metadata")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity> getAllPackMetadata();
    
    @androidx.room.Query(value = "DELETE FROM board_questions")
    public abstract void deleteAllQuestions();
    
    @androidx.room.Query(value = "DELETE FROM board_pack_metadata")
    public abstract void deleteAllPackMetadata();
    
    @androidx.room.Query(value = "DELETE FROM board_user_progress")
    public abstract void deleteAllUserProgress();
    
    @androidx.room.Transaction()
    public abstract void importPack(@org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity> questions, @org.jetbrains.annotations.NotNull()
    com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity metadata);
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        @androidx.room.Transaction()
        public static void importPack(@org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDao $this, @org.jetbrains.annotations.NotNull()
        java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity> questions, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity metadata) {
        }
    }
}