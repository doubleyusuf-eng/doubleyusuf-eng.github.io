package com.anesthesiaclinic.anesthesiabriefs.data.local;

@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "boardPrepDao", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity.class, com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity.class, com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity.class}, version = 2, exportSchema = false)
public abstract class BoardPrepDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDatabase.Companion Companion = null;
    
    public BoardPrepDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDao boardPrepDao();
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/anesthesiaclinic/anesthesiabriefs/data/local/BoardPrepDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.local.BoardPrepDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}