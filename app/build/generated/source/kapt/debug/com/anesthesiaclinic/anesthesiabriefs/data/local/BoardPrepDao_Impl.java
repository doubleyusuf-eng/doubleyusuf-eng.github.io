package com.anesthesiaclinic.anesthesiabriefs.data.local;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BoardPrepDao_Impl implements BoardPrepDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QuestionEntity> __insertionAdapterOfQuestionEntity;

  private final EntityInsertionAdapter<UserProgressEntity> __insertionAdapterOfUserProgressEntity;

  private final EntityInsertionAdapter<PackMetadataEntity> __insertionAdapterOfPackMetadataEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteQuestionsByBoardType;

  private final SharedSQLiteStatement __preparedStmtOfUpdateQuestionBookmark;

  private final SharedSQLiteStatement __preparedStmtOfUpdateProgressBookmark;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllQuestions;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPackMetadata;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUserProgress;

  public BoardPrepDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestionEntity = new EntityInsertionAdapter<QuestionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `board_questions` (`id`,`packId`,`boardType`,`questionType`,`category`,`topic`,`difficulty`,`stemEn`,`stemTr`,`sourceType`,`version`,`isBookmarked`,`tagsJson`,`questionJson`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QuestionEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getPackId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPackId());
        }
        if (entity.getBoardType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBoardType());
        }
        if (entity.getQuestionType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getQuestionType());
        }
        if (entity.getCategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategory());
        }
        if (entity.getTopic() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getTopic());
        }
        if (entity.getDifficulty() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDifficulty());
        }
        if (entity.getStemEn() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStemEn());
        }
        if (entity.getStemTr() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getStemTr());
        }
        if (entity.getSourceType() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getSourceType());
        }
        statement.bindLong(11, entity.getVersion());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(12, _tmp);
        if (entity.getTagsJson() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getTagsJson());
        }
        if (entity.getQuestionJson() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getQuestionJson());
        }
      }
    };
    this.__insertionAdapterOfUserProgressEntity = new EntityInsertionAdapter<UserProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `board_user_progress` (`questionId`,`selectedAnswersJson`,`isCorrectStrict`,`statementsCorrectCount`,`attemptCount`,`lastAnsweredAt`,`bookmarked`,`markedDifficult`,`weakTopicTagsJson`,`vivaSelfRating`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProgressEntity entity) {
        if (entity.getQuestionId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getQuestionId());
        }
        if (entity.getSelectedAnswersJson() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSelectedAnswersJson());
        }
        final int _tmp = entity.isCorrectStrict() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getStatementsCorrectCount());
        statement.bindLong(5, entity.getAttemptCount());
        statement.bindLong(6, entity.getLastAnsweredAt());
        final int _tmp_1 = entity.getBookmarked() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        final int _tmp_2 = entity.getMarkedDifficult() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        if (entity.getWeakTopicTagsJson() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getWeakTopicTagsJson());
        }
        if (entity.getVivaSelfRating() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getVivaSelfRating());
        }
      }
    };
    this.__insertionAdapterOfPackMetadataEntity = new EntityInsertionAdapter<PackMetadataEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `board_pack_metadata` (`packId`,`version`,`checksumSha256`,`installedAt`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PackMetadataEntity entity) {
        if (entity.getPackId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getPackId());
        }
        statement.bindLong(2, entity.getVersion());
        if (entity.getChecksumSha256() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getChecksumSha256());
        }
        statement.bindLong(4, entity.getInstalledAt());
      }
    };
    this.__preparedStmtOfDeleteQuestionsByBoardType = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM board_questions WHERE boardType = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateQuestionBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE board_questions SET isBookmarked = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateProgressBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE board_user_progress SET bookmarked = ? WHERE questionId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllQuestions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM board_questions";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPackMetadata = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM board_pack_metadata";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllUserProgress = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM board_user_progress";
        return _query;
      }
    };
  }

  @Override
  public void insertQuestions(final List<QuestionEntity> questions) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestionEntity.insert(questions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertUserProgress(final UserProgressEntity progress) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUserProgressEntity.insert(progress);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertPackMetadata(final PackMetadataEntity pack) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPackMetadataEntity.insert(pack);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void importPack(final List<QuestionEntity> questions, final PackMetadataEntity metadata) {
    __db.beginTransaction();
    try {
      BoardPrepDao.DefaultImpls.importPack(BoardPrepDao_Impl.this, questions, metadata);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteQuestionsByBoardType(final String boardType) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteQuestionsByBoardType.acquire();
    int _argIndex = 1;
    if (boardType == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, boardType);
    }
    try {
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteQuestionsByBoardType.release(_stmt);
    }
  }

  @Override
  public int updateQuestionBookmark(final String questionId, final boolean isBookmarked) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateQuestionBookmark.acquire();
    int _argIndex = 1;
    final int _tmp = isBookmarked ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    if (questionId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, questionId);
    }
    try {
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateQuestionBookmark.release(_stmt);
    }
  }

  @Override
  public int updateProgressBookmark(final String questionId, final boolean bookmarked) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateProgressBookmark.acquire();
    int _argIndex = 1;
    final int _tmp = bookmarked ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    if (questionId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, questionId);
    }
    try {
      __db.beginTransaction();
      try {
        final int _result = _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateProgressBookmark.release(_stmt);
    }
  }

  @Override
  public void deleteAllQuestions() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllQuestions.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllQuestions.release(_stmt);
    }
  }

  @Override
  public void deleteAllPackMetadata() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPackMetadata.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllPackMetadata.release(_stmt);
    }
  }

  @Override
  public void deleteAllUserProgress() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUserProgress.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAllUserProgress.release(_stmt);
    }
  }

  @Override
  public List<QuestionEntity> getQuestionsByBoardType(final String boardType) {
    final String _sql = "SELECT * FROM board_questions WHERE boardType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (boardType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, boardType);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
      final int _cursorIndexOfBoardType = CursorUtil.getColumnIndexOrThrow(_cursor, "boardType");
      final int _cursorIndexOfQuestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "questionType");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "topic");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfStemEn = CursorUtil.getColumnIndexOrThrow(_cursor, "stemEn");
      final int _cursorIndexOfStemTr = CursorUtil.getColumnIndexOrThrow(_cursor, "stemTr");
      final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
      final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
      final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
      final int _cursorIndexOfTagsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsJson");
      final int _cursorIndexOfQuestionJson = CursorUtil.getColumnIndexOrThrow(_cursor, "questionJson");
      final List<QuestionEntity> _result = new ArrayList<QuestionEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final QuestionEntity _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpPackId;
        if (_cursor.isNull(_cursorIndexOfPackId)) {
          _tmpPackId = null;
        } else {
          _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
        }
        final String _tmpBoardType;
        if (_cursor.isNull(_cursorIndexOfBoardType)) {
          _tmpBoardType = null;
        } else {
          _tmpBoardType = _cursor.getString(_cursorIndexOfBoardType);
        }
        final String _tmpQuestionType;
        if (_cursor.isNull(_cursorIndexOfQuestionType)) {
          _tmpQuestionType = null;
        } else {
          _tmpQuestionType = _cursor.getString(_cursorIndexOfQuestionType);
        }
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        final String _tmpTopic;
        if (_cursor.isNull(_cursorIndexOfTopic)) {
          _tmpTopic = null;
        } else {
          _tmpTopic = _cursor.getString(_cursorIndexOfTopic);
        }
        final String _tmpDifficulty;
        if (_cursor.isNull(_cursorIndexOfDifficulty)) {
          _tmpDifficulty = null;
        } else {
          _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
        }
        final String _tmpStemEn;
        if (_cursor.isNull(_cursorIndexOfStemEn)) {
          _tmpStemEn = null;
        } else {
          _tmpStemEn = _cursor.getString(_cursorIndexOfStemEn);
        }
        final String _tmpStemTr;
        if (_cursor.isNull(_cursorIndexOfStemTr)) {
          _tmpStemTr = null;
        } else {
          _tmpStemTr = _cursor.getString(_cursorIndexOfStemTr);
        }
        final String _tmpSourceType;
        if (_cursor.isNull(_cursorIndexOfSourceType)) {
          _tmpSourceType = null;
        } else {
          _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
        }
        final int _tmpVersion;
        _tmpVersion = _cursor.getInt(_cursorIndexOfVersion);
        final boolean _tmpIsBookmarked;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
        _tmpIsBookmarked = _tmp != 0;
        final String _tmpTagsJson;
        if (_cursor.isNull(_cursorIndexOfTagsJson)) {
          _tmpTagsJson = null;
        } else {
          _tmpTagsJson = _cursor.getString(_cursorIndexOfTagsJson);
        }
        final String _tmpQuestionJson;
        if (_cursor.isNull(_cursorIndexOfQuestionJson)) {
          _tmpQuestionJson = null;
        } else {
          _tmpQuestionJson = _cursor.getString(_cursorIndexOfQuestionJson);
        }
        _item = new QuestionEntity(_tmpId,_tmpPackId,_tmpBoardType,_tmpQuestionType,_tmpCategory,_tmpTopic,_tmpDifficulty,_tmpStemEn,_tmpStemTr,_tmpSourceType,_tmpVersion,_tmpIsBookmarked,_tmpTagsJson,_tmpQuestionJson);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public QuestionEntity getQuestionById(final String id) {
    final String _sql = "SELECT * FROM board_questions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
      final int _cursorIndexOfBoardType = CursorUtil.getColumnIndexOrThrow(_cursor, "boardType");
      final int _cursorIndexOfQuestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "questionType");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "topic");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfStemEn = CursorUtil.getColumnIndexOrThrow(_cursor, "stemEn");
      final int _cursorIndexOfStemTr = CursorUtil.getColumnIndexOrThrow(_cursor, "stemTr");
      final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
      final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
      final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
      final int _cursorIndexOfTagsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsJson");
      final int _cursorIndexOfQuestionJson = CursorUtil.getColumnIndexOrThrow(_cursor, "questionJson");
      final QuestionEntity _result;
      if (_cursor.moveToFirst()) {
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpPackId;
        if (_cursor.isNull(_cursorIndexOfPackId)) {
          _tmpPackId = null;
        } else {
          _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
        }
        final String _tmpBoardType;
        if (_cursor.isNull(_cursorIndexOfBoardType)) {
          _tmpBoardType = null;
        } else {
          _tmpBoardType = _cursor.getString(_cursorIndexOfBoardType);
        }
        final String _tmpQuestionType;
        if (_cursor.isNull(_cursorIndexOfQuestionType)) {
          _tmpQuestionType = null;
        } else {
          _tmpQuestionType = _cursor.getString(_cursorIndexOfQuestionType);
        }
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        final String _tmpTopic;
        if (_cursor.isNull(_cursorIndexOfTopic)) {
          _tmpTopic = null;
        } else {
          _tmpTopic = _cursor.getString(_cursorIndexOfTopic);
        }
        final String _tmpDifficulty;
        if (_cursor.isNull(_cursorIndexOfDifficulty)) {
          _tmpDifficulty = null;
        } else {
          _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
        }
        final String _tmpStemEn;
        if (_cursor.isNull(_cursorIndexOfStemEn)) {
          _tmpStemEn = null;
        } else {
          _tmpStemEn = _cursor.getString(_cursorIndexOfStemEn);
        }
        final String _tmpStemTr;
        if (_cursor.isNull(_cursorIndexOfStemTr)) {
          _tmpStemTr = null;
        } else {
          _tmpStemTr = _cursor.getString(_cursorIndexOfStemTr);
        }
        final String _tmpSourceType;
        if (_cursor.isNull(_cursorIndexOfSourceType)) {
          _tmpSourceType = null;
        } else {
          _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
        }
        final int _tmpVersion;
        _tmpVersion = _cursor.getInt(_cursorIndexOfVersion);
        final boolean _tmpIsBookmarked;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
        _tmpIsBookmarked = _tmp != 0;
        final String _tmpTagsJson;
        if (_cursor.isNull(_cursorIndexOfTagsJson)) {
          _tmpTagsJson = null;
        } else {
          _tmpTagsJson = _cursor.getString(_cursorIndexOfTagsJson);
        }
        final String _tmpQuestionJson;
        if (_cursor.isNull(_cursorIndexOfQuestionJson)) {
          _tmpQuestionJson = null;
        } else {
          _tmpQuestionJson = _cursor.getString(_cursorIndexOfQuestionJson);
        }
        _result = new QuestionEntity(_tmpId,_tmpPackId,_tmpBoardType,_tmpQuestionType,_tmpCategory,_tmpTopic,_tmpDifficulty,_tmpStemEn,_tmpStemTr,_tmpSourceType,_tmpVersion,_tmpIsBookmarked,_tmpTagsJson,_tmpQuestionJson);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QuestionEntity> getBookmarkedQuestions() {
    final String _sql = "SELECT * FROM board_questions WHERE isBookmarked = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
      final int _cursorIndexOfBoardType = CursorUtil.getColumnIndexOrThrow(_cursor, "boardType");
      final int _cursorIndexOfQuestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "questionType");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "topic");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfStemEn = CursorUtil.getColumnIndexOrThrow(_cursor, "stemEn");
      final int _cursorIndexOfStemTr = CursorUtil.getColumnIndexOrThrow(_cursor, "stemTr");
      final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
      final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
      final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
      final int _cursorIndexOfTagsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsJson");
      final int _cursorIndexOfQuestionJson = CursorUtil.getColumnIndexOrThrow(_cursor, "questionJson");
      final List<QuestionEntity> _result = new ArrayList<QuestionEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final QuestionEntity _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpPackId;
        if (_cursor.isNull(_cursorIndexOfPackId)) {
          _tmpPackId = null;
        } else {
          _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
        }
        final String _tmpBoardType;
        if (_cursor.isNull(_cursorIndexOfBoardType)) {
          _tmpBoardType = null;
        } else {
          _tmpBoardType = _cursor.getString(_cursorIndexOfBoardType);
        }
        final String _tmpQuestionType;
        if (_cursor.isNull(_cursorIndexOfQuestionType)) {
          _tmpQuestionType = null;
        } else {
          _tmpQuestionType = _cursor.getString(_cursorIndexOfQuestionType);
        }
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        final String _tmpTopic;
        if (_cursor.isNull(_cursorIndexOfTopic)) {
          _tmpTopic = null;
        } else {
          _tmpTopic = _cursor.getString(_cursorIndexOfTopic);
        }
        final String _tmpDifficulty;
        if (_cursor.isNull(_cursorIndexOfDifficulty)) {
          _tmpDifficulty = null;
        } else {
          _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
        }
        final String _tmpStemEn;
        if (_cursor.isNull(_cursorIndexOfStemEn)) {
          _tmpStemEn = null;
        } else {
          _tmpStemEn = _cursor.getString(_cursorIndexOfStemEn);
        }
        final String _tmpStemTr;
        if (_cursor.isNull(_cursorIndexOfStemTr)) {
          _tmpStemTr = null;
        } else {
          _tmpStemTr = _cursor.getString(_cursorIndexOfStemTr);
        }
        final String _tmpSourceType;
        if (_cursor.isNull(_cursorIndexOfSourceType)) {
          _tmpSourceType = null;
        } else {
          _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
        }
        final int _tmpVersion;
        _tmpVersion = _cursor.getInt(_cursorIndexOfVersion);
        final boolean _tmpIsBookmarked;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
        _tmpIsBookmarked = _tmp != 0;
        final String _tmpTagsJson;
        if (_cursor.isNull(_cursorIndexOfTagsJson)) {
          _tmpTagsJson = null;
        } else {
          _tmpTagsJson = _cursor.getString(_cursorIndexOfTagsJson);
        }
        final String _tmpQuestionJson;
        if (_cursor.isNull(_cursorIndexOfQuestionJson)) {
          _tmpQuestionJson = null;
        } else {
          _tmpQuestionJson = _cursor.getString(_cursorIndexOfQuestionJson);
        }
        _item = new QuestionEntity(_tmpId,_tmpPackId,_tmpBoardType,_tmpQuestionType,_tmpCategory,_tmpTopic,_tmpDifficulty,_tmpStemEn,_tmpStemTr,_tmpSourceType,_tmpVersion,_tmpIsBookmarked,_tmpTagsJson,_tmpQuestionJson);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<QuestionEntity> getAllQuestions() {
    final String _sql = "SELECT * FROM board_questions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
      final int _cursorIndexOfBoardType = CursorUtil.getColumnIndexOrThrow(_cursor, "boardType");
      final int _cursorIndexOfQuestionType = CursorUtil.getColumnIndexOrThrow(_cursor, "questionType");
      final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
      final int _cursorIndexOfTopic = CursorUtil.getColumnIndexOrThrow(_cursor, "topic");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfStemEn = CursorUtil.getColumnIndexOrThrow(_cursor, "stemEn");
      final int _cursorIndexOfStemTr = CursorUtil.getColumnIndexOrThrow(_cursor, "stemTr");
      final int _cursorIndexOfSourceType = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceType");
      final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
      final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
      final int _cursorIndexOfTagsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "tagsJson");
      final int _cursorIndexOfQuestionJson = CursorUtil.getColumnIndexOrThrow(_cursor, "questionJson");
      final List<QuestionEntity> _result = new ArrayList<QuestionEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final QuestionEntity _item;
        final String _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getString(_cursorIndexOfId);
        }
        final String _tmpPackId;
        if (_cursor.isNull(_cursorIndexOfPackId)) {
          _tmpPackId = null;
        } else {
          _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
        }
        final String _tmpBoardType;
        if (_cursor.isNull(_cursorIndexOfBoardType)) {
          _tmpBoardType = null;
        } else {
          _tmpBoardType = _cursor.getString(_cursorIndexOfBoardType);
        }
        final String _tmpQuestionType;
        if (_cursor.isNull(_cursorIndexOfQuestionType)) {
          _tmpQuestionType = null;
        } else {
          _tmpQuestionType = _cursor.getString(_cursorIndexOfQuestionType);
        }
        final String _tmpCategory;
        if (_cursor.isNull(_cursorIndexOfCategory)) {
          _tmpCategory = null;
        } else {
          _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
        }
        final String _tmpTopic;
        if (_cursor.isNull(_cursorIndexOfTopic)) {
          _tmpTopic = null;
        } else {
          _tmpTopic = _cursor.getString(_cursorIndexOfTopic);
        }
        final String _tmpDifficulty;
        if (_cursor.isNull(_cursorIndexOfDifficulty)) {
          _tmpDifficulty = null;
        } else {
          _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
        }
        final String _tmpStemEn;
        if (_cursor.isNull(_cursorIndexOfStemEn)) {
          _tmpStemEn = null;
        } else {
          _tmpStemEn = _cursor.getString(_cursorIndexOfStemEn);
        }
        final String _tmpStemTr;
        if (_cursor.isNull(_cursorIndexOfStemTr)) {
          _tmpStemTr = null;
        } else {
          _tmpStemTr = _cursor.getString(_cursorIndexOfStemTr);
        }
        final String _tmpSourceType;
        if (_cursor.isNull(_cursorIndexOfSourceType)) {
          _tmpSourceType = null;
        } else {
          _tmpSourceType = _cursor.getString(_cursorIndexOfSourceType);
        }
        final int _tmpVersion;
        _tmpVersion = _cursor.getInt(_cursorIndexOfVersion);
        final boolean _tmpIsBookmarked;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
        _tmpIsBookmarked = _tmp != 0;
        final String _tmpTagsJson;
        if (_cursor.isNull(_cursorIndexOfTagsJson)) {
          _tmpTagsJson = null;
        } else {
          _tmpTagsJson = _cursor.getString(_cursorIndexOfTagsJson);
        }
        final String _tmpQuestionJson;
        if (_cursor.isNull(_cursorIndexOfQuestionJson)) {
          _tmpQuestionJson = null;
        } else {
          _tmpQuestionJson = _cursor.getString(_cursorIndexOfQuestionJson);
        }
        _item = new QuestionEntity(_tmpId,_tmpPackId,_tmpBoardType,_tmpQuestionType,_tmpCategory,_tmpTopic,_tmpDifficulty,_tmpStemEn,_tmpStemTr,_tmpSourceType,_tmpVersion,_tmpIsBookmarked,_tmpTagsJson,_tmpQuestionJson);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public UserProgressEntity getUserProgress(final String questionId) {
    final String _sql = "SELECT * FROM board_user_progress WHERE questionId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (questionId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, questionId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfQuestionId = CursorUtil.getColumnIndexOrThrow(_cursor, "questionId");
      final int _cursorIndexOfSelectedAnswersJson = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedAnswersJson");
      final int _cursorIndexOfIsCorrectStrict = CursorUtil.getColumnIndexOrThrow(_cursor, "isCorrectStrict");
      final int _cursorIndexOfStatementsCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "statementsCorrectCount");
      final int _cursorIndexOfAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "attemptCount");
      final int _cursorIndexOfLastAnsweredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAnsweredAt");
      final int _cursorIndexOfBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarked");
      final int _cursorIndexOfMarkedDifficult = CursorUtil.getColumnIndexOrThrow(_cursor, "markedDifficult");
      final int _cursorIndexOfWeakTopicTagsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "weakTopicTagsJson");
      final int _cursorIndexOfVivaSelfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "vivaSelfRating");
      final UserProgressEntity _result;
      if (_cursor.moveToFirst()) {
        final String _tmpQuestionId;
        if (_cursor.isNull(_cursorIndexOfQuestionId)) {
          _tmpQuestionId = null;
        } else {
          _tmpQuestionId = _cursor.getString(_cursorIndexOfQuestionId);
        }
        final String _tmpSelectedAnswersJson;
        if (_cursor.isNull(_cursorIndexOfSelectedAnswersJson)) {
          _tmpSelectedAnswersJson = null;
        } else {
          _tmpSelectedAnswersJson = _cursor.getString(_cursorIndexOfSelectedAnswersJson);
        }
        final boolean _tmpIsCorrectStrict;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsCorrectStrict);
        _tmpIsCorrectStrict = _tmp != 0;
        final int _tmpStatementsCorrectCount;
        _tmpStatementsCorrectCount = _cursor.getInt(_cursorIndexOfStatementsCorrectCount);
        final int _tmpAttemptCount;
        _tmpAttemptCount = _cursor.getInt(_cursorIndexOfAttemptCount);
        final long _tmpLastAnsweredAt;
        _tmpLastAnsweredAt = _cursor.getLong(_cursorIndexOfLastAnsweredAt);
        final boolean _tmpBookmarked;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfBookmarked);
        _tmpBookmarked = _tmp_1 != 0;
        final boolean _tmpMarkedDifficult;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfMarkedDifficult);
        _tmpMarkedDifficult = _tmp_2 != 0;
        final String _tmpWeakTopicTagsJson;
        if (_cursor.isNull(_cursorIndexOfWeakTopicTagsJson)) {
          _tmpWeakTopicTagsJson = null;
        } else {
          _tmpWeakTopicTagsJson = _cursor.getString(_cursorIndexOfWeakTopicTagsJson);
        }
        final String _tmpVivaSelfRating;
        if (_cursor.isNull(_cursorIndexOfVivaSelfRating)) {
          _tmpVivaSelfRating = null;
        } else {
          _tmpVivaSelfRating = _cursor.getString(_cursorIndexOfVivaSelfRating);
        }
        _result = new UserProgressEntity(_tmpQuestionId,_tmpSelectedAnswersJson,_tmpIsCorrectStrict,_tmpStatementsCorrectCount,_tmpAttemptCount,_tmpLastAnsweredAt,_tmpBookmarked,_tmpMarkedDifficult,_tmpWeakTopicTagsJson,_tmpVivaSelfRating);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UserProgressEntity> getAllUserProgress() {
    final String _sql = "SELECT * FROM board_user_progress";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfQuestionId = CursorUtil.getColumnIndexOrThrow(_cursor, "questionId");
      final int _cursorIndexOfSelectedAnswersJson = CursorUtil.getColumnIndexOrThrow(_cursor, "selectedAnswersJson");
      final int _cursorIndexOfIsCorrectStrict = CursorUtil.getColumnIndexOrThrow(_cursor, "isCorrectStrict");
      final int _cursorIndexOfStatementsCorrectCount = CursorUtil.getColumnIndexOrThrow(_cursor, "statementsCorrectCount");
      final int _cursorIndexOfAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "attemptCount");
      final int _cursorIndexOfLastAnsweredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastAnsweredAt");
      final int _cursorIndexOfBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarked");
      final int _cursorIndexOfMarkedDifficult = CursorUtil.getColumnIndexOrThrow(_cursor, "markedDifficult");
      final int _cursorIndexOfWeakTopicTagsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "weakTopicTagsJson");
      final int _cursorIndexOfVivaSelfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "vivaSelfRating");
      final List<UserProgressEntity> _result = new ArrayList<UserProgressEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final UserProgressEntity _item;
        final String _tmpQuestionId;
        if (_cursor.isNull(_cursorIndexOfQuestionId)) {
          _tmpQuestionId = null;
        } else {
          _tmpQuestionId = _cursor.getString(_cursorIndexOfQuestionId);
        }
        final String _tmpSelectedAnswersJson;
        if (_cursor.isNull(_cursorIndexOfSelectedAnswersJson)) {
          _tmpSelectedAnswersJson = null;
        } else {
          _tmpSelectedAnswersJson = _cursor.getString(_cursorIndexOfSelectedAnswersJson);
        }
        final boolean _tmpIsCorrectStrict;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsCorrectStrict);
        _tmpIsCorrectStrict = _tmp != 0;
        final int _tmpStatementsCorrectCount;
        _tmpStatementsCorrectCount = _cursor.getInt(_cursorIndexOfStatementsCorrectCount);
        final int _tmpAttemptCount;
        _tmpAttemptCount = _cursor.getInt(_cursorIndexOfAttemptCount);
        final long _tmpLastAnsweredAt;
        _tmpLastAnsweredAt = _cursor.getLong(_cursorIndexOfLastAnsweredAt);
        final boolean _tmpBookmarked;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfBookmarked);
        _tmpBookmarked = _tmp_1 != 0;
        final boolean _tmpMarkedDifficult;
        final int _tmp_2;
        _tmp_2 = _cursor.getInt(_cursorIndexOfMarkedDifficult);
        _tmpMarkedDifficult = _tmp_2 != 0;
        final String _tmpWeakTopicTagsJson;
        if (_cursor.isNull(_cursorIndexOfWeakTopicTagsJson)) {
          _tmpWeakTopicTagsJson = null;
        } else {
          _tmpWeakTopicTagsJson = _cursor.getString(_cursorIndexOfWeakTopicTagsJson);
        }
        final String _tmpVivaSelfRating;
        if (_cursor.isNull(_cursorIndexOfVivaSelfRating)) {
          _tmpVivaSelfRating = null;
        } else {
          _tmpVivaSelfRating = _cursor.getString(_cursorIndexOfVivaSelfRating);
        }
        _item = new UserProgressEntity(_tmpQuestionId,_tmpSelectedAnswersJson,_tmpIsCorrectStrict,_tmpStatementsCorrectCount,_tmpAttemptCount,_tmpLastAnsweredAt,_tmpBookmarked,_tmpMarkedDifficult,_tmpWeakTopicTagsJson,_tmpVivaSelfRating);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public PackMetadataEntity getPackMetadata(final String packId) {
    final String _sql = "SELECT * FROM board_pack_metadata WHERE packId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (packId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, packId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
      final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
      final int _cursorIndexOfChecksumSha256 = CursorUtil.getColumnIndexOrThrow(_cursor, "checksumSha256");
      final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
      final PackMetadataEntity _result;
      if (_cursor.moveToFirst()) {
        final String _tmpPackId;
        if (_cursor.isNull(_cursorIndexOfPackId)) {
          _tmpPackId = null;
        } else {
          _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
        }
        final int _tmpVersion;
        _tmpVersion = _cursor.getInt(_cursorIndexOfVersion);
        final String _tmpChecksumSha256;
        if (_cursor.isNull(_cursorIndexOfChecksumSha256)) {
          _tmpChecksumSha256 = null;
        } else {
          _tmpChecksumSha256 = _cursor.getString(_cursorIndexOfChecksumSha256);
        }
        final long _tmpInstalledAt;
        _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
        _result = new PackMetadataEntity(_tmpPackId,_tmpVersion,_tmpChecksumSha256,_tmpInstalledAt);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<PackMetadataEntity> getAllPackMetadata() {
    final String _sql = "SELECT * FROM board_pack_metadata";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPackId = CursorUtil.getColumnIndexOrThrow(_cursor, "packId");
      final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
      final int _cursorIndexOfChecksumSha256 = CursorUtil.getColumnIndexOrThrow(_cursor, "checksumSha256");
      final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
      final List<PackMetadataEntity> _result = new ArrayList<PackMetadataEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final PackMetadataEntity _item;
        final String _tmpPackId;
        if (_cursor.isNull(_cursorIndexOfPackId)) {
          _tmpPackId = null;
        } else {
          _tmpPackId = _cursor.getString(_cursorIndexOfPackId);
        }
        final int _tmpVersion;
        _tmpVersion = _cursor.getInt(_cursorIndexOfVersion);
        final String _tmpChecksumSha256;
        if (_cursor.isNull(_cursorIndexOfChecksumSha256)) {
          _tmpChecksumSha256 = null;
        } else {
          _tmpChecksumSha256 = _cursor.getString(_cursorIndexOfChecksumSha256);
        }
        final long _tmpInstalledAt;
        _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
        _item = new PackMetadataEntity(_tmpPackId,_tmpVersion,_tmpChecksumSha256,_tmpInstalledAt);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
