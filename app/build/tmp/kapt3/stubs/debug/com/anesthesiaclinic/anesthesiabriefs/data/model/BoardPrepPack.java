package com.anesthesiaclinic.anesthesiabriefs.data.model;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\b\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0\bH\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\bH\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u00c6\u0003Jg\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017\u00a8\u0006*"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPack;", "", "packId", "", "version", "", "boardType", "languages", "", "edaicMtfQuestions", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/EdaicMtfQuestion;", "sbaQuestions", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/SingleBestAnswerQuestion;", "vivaQuestions", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/VivaScenarioQuestion;", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getPackId", "()Ljava/lang/String;", "getVersion", "()I", "getBoardType", "getLanguages", "()Ljava/util/List;", "getEdaicMtfQuestions", "getSbaQuestions", "getVivaQuestions", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "app_debug"})
public final class BoardPrepPack {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packId = null;
    private final int version = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String boardType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> languages = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> edaicMtfQuestions = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> sbaQuestions = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion> vivaQuestions = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack.Companion Companion = null;
    
    public BoardPrepPack(@org.jetbrains.annotations.NotNull()
    java.lang.String packId, int version, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> languages, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> edaicMtfQuestions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> sbaQuestions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion> vivaQuestions) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackId() {
        return null;
    }
    
    public final int getVersion() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBoardType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLanguages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> getEdaicMtfQuestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> getSbaQuestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion> getVivaQuestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack copy(@org.jetbrains.annotations.NotNull()
    java.lang.String packId, int version, @org.jetbrains.annotations.NotNull()
    java.lang.String boardType, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> languages, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.EdaicMtfQuestion> edaicMtfQuestions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.SingleBestAnswerQuestion> sbaQuestions, @org.jetbrains.annotations.NotNull()
    java.util.List<com.anesthesiaclinic.anesthesiabriefs.data.model.VivaScenarioQuestion> vivaQuestions) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"com/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPack.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPack;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack> {
        @org.jetbrains.annotations.NotNull()
        public static final com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack.$serializer INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final kotlinx.serialization.descriptors.SerialDescriptor descriptor = null;
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        public final void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack value) {
        }
        
        private $serializer() {
            super();
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPack$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/BoardPrepPack;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.BoardPrepPack> serializer() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}