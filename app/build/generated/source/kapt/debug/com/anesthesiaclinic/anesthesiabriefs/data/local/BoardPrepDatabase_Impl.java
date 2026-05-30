package com.anesthesiaclinic.anesthesiabriefs.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BoardPrepDatabase_Impl extends BoardPrepDatabase {
  private volatile BoardPrepDao _boardPrepDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `board_questions` (`id` TEXT NOT NULL, `packId` TEXT NOT NULL, `boardType` TEXT NOT NULL, `questionType` TEXT NOT NULL, `category` TEXT NOT NULL, `topic` TEXT NOT NULL, `difficulty` TEXT NOT NULL, `stemEn` TEXT NOT NULL, `stemTr` TEXT NOT NULL, `sourceType` TEXT NOT NULL, `version` INTEGER NOT NULL, `isBookmarked` INTEGER NOT NULL, `tagsJson` TEXT NOT NULL, `questionJson` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `board_user_progress` (`questionId` TEXT NOT NULL, `selectedAnswersJson` TEXT NOT NULL, `isCorrectStrict` INTEGER NOT NULL, `statementsCorrectCount` INTEGER NOT NULL, `attemptCount` INTEGER NOT NULL, `lastAnsweredAt` INTEGER NOT NULL, `bookmarked` INTEGER NOT NULL, `markedDifficult` INTEGER NOT NULL, `weakTopicTagsJson` TEXT NOT NULL, `vivaSelfRating` TEXT, PRIMARY KEY(`questionId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `board_pack_metadata` (`packId` TEXT NOT NULL, `version` INTEGER NOT NULL, `checksumSha256` TEXT NOT NULL, `installedAt` INTEGER NOT NULL, PRIMARY KEY(`packId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '3a73dd2ea100a289a64efaf7b44d2a2a')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `board_questions`");
        db.execSQL("DROP TABLE IF EXISTS `board_user_progress`");
        db.execSQL("DROP TABLE IF EXISTS `board_pack_metadata`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsBoardQuestions = new HashMap<String, TableInfo.Column>(14);
        _columnsBoardQuestions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("packId", new TableInfo.Column("packId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("boardType", new TableInfo.Column("boardType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("questionType", new TableInfo.Column("questionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("topic", new TableInfo.Column("topic", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("difficulty", new TableInfo.Column("difficulty", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("stemEn", new TableInfo.Column("stemEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("stemTr", new TableInfo.Column("stemTr", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("sourceType", new TableInfo.Column("sourceType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("version", new TableInfo.Column("version", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("isBookmarked", new TableInfo.Column("isBookmarked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("tagsJson", new TableInfo.Column("tagsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardQuestions.put("questionJson", new TableInfo.Column("questionJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBoardQuestions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBoardQuestions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBoardQuestions = new TableInfo("board_questions", _columnsBoardQuestions, _foreignKeysBoardQuestions, _indicesBoardQuestions);
        final TableInfo _existingBoardQuestions = TableInfo.read(db, "board_questions");
        if (!_infoBoardQuestions.equals(_existingBoardQuestions)) {
          return new RoomOpenHelper.ValidationResult(false, "board_questions(com.anesthesiaclinic.anesthesiabriefs.data.local.QuestionEntity).\n"
                  + " Expected:\n" + _infoBoardQuestions + "\n"
                  + " Found:\n" + _existingBoardQuestions);
        }
        final HashMap<String, TableInfo.Column> _columnsBoardUserProgress = new HashMap<String, TableInfo.Column>(10);
        _columnsBoardUserProgress.put("questionId", new TableInfo.Column("questionId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("selectedAnswersJson", new TableInfo.Column("selectedAnswersJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("isCorrectStrict", new TableInfo.Column("isCorrectStrict", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("statementsCorrectCount", new TableInfo.Column("statementsCorrectCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("attemptCount", new TableInfo.Column("attemptCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("lastAnsweredAt", new TableInfo.Column("lastAnsweredAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("bookmarked", new TableInfo.Column("bookmarked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("markedDifficult", new TableInfo.Column("markedDifficult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("weakTopicTagsJson", new TableInfo.Column("weakTopicTagsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardUserProgress.put("vivaSelfRating", new TableInfo.Column("vivaSelfRating", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBoardUserProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBoardUserProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBoardUserProgress = new TableInfo("board_user_progress", _columnsBoardUserProgress, _foreignKeysBoardUserProgress, _indicesBoardUserProgress);
        final TableInfo _existingBoardUserProgress = TableInfo.read(db, "board_user_progress");
        if (!_infoBoardUserProgress.equals(_existingBoardUserProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "board_user_progress(com.anesthesiaclinic.anesthesiabriefs.data.local.UserProgressEntity).\n"
                  + " Expected:\n" + _infoBoardUserProgress + "\n"
                  + " Found:\n" + _existingBoardUserProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsBoardPackMetadata = new HashMap<String, TableInfo.Column>(4);
        _columnsBoardPackMetadata.put("packId", new TableInfo.Column("packId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardPackMetadata.put("version", new TableInfo.Column("version", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardPackMetadata.put("checksumSha256", new TableInfo.Column("checksumSha256", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoardPackMetadata.put("installedAt", new TableInfo.Column("installedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBoardPackMetadata = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBoardPackMetadata = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBoardPackMetadata = new TableInfo("board_pack_metadata", _columnsBoardPackMetadata, _foreignKeysBoardPackMetadata, _indicesBoardPackMetadata);
        final TableInfo _existingBoardPackMetadata = TableInfo.read(db, "board_pack_metadata");
        if (!_infoBoardPackMetadata.equals(_existingBoardPackMetadata)) {
          return new RoomOpenHelper.ValidationResult(false, "board_pack_metadata(com.anesthesiaclinic.anesthesiabriefs.data.local.PackMetadataEntity).\n"
                  + " Expected:\n" + _infoBoardPackMetadata + "\n"
                  + " Found:\n" + _existingBoardPackMetadata);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "3a73dd2ea100a289a64efaf7b44d2a2a", "a269ba54e4dcc41114207a8ab7da0cae");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "board_questions","board_user_progress","board_pack_metadata");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `board_questions`");
      _db.execSQL("DELETE FROM `board_user_progress`");
      _db.execSQL("DELETE FROM `board_pack_metadata`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(BoardPrepDao.class, BoardPrepDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public BoardPrepDao boardPrepDao() {
    if (_boardPrepDao != null) {
      return _boardPrepDao;
    } else {
      synchronized(this) {
        if(_boardPrepDao == null) {
          _boardPrepDao = new BoardPrepDao_Impl(this);
        }
        return _boardPrepDao;
      }
    }
  }
}
