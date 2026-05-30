package com.anesthesiaclinic.anesthesiabriefs.data.model;

@kotlinx.serialization.Serializable()
@kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\bF\n\u0002\u0010\b\n\u0002\b\u0004\b\u0087\b\u0018\u0000 m2\u00020\u0001:\u0002lmB\u00f9\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u0012\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020#\u0012\b\b\u0002\u0010$\u001a\u00020#\u00a2\u0006\u0004\b%\u0010&J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0003H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0003J\u0015\u0010S\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0003J\u0015\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eH\u00c6\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u000f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000f\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u001cH\u00c6\u0003\u00a2\u0006\u0002\u0010@J\u0010\u0010^\u001a\u0004\u0018\u00010\u001cH\u00c6\u0003\u00a2\u0006\u0002\u0010@J\u0010\u0010_\u001a\u0004\u0018\u00010\u001cH\u00c6\u0003\u00a2\u0006\u0002\u0010@J\u000f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010c\u001a\u00020#H\u00c6\u0003J\t\u0010d\u001a\u00020#H\u00c6\u0003J\u008a\u0003\u0010e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e2\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020#H\u00c6\u0001\u00a2\u0006\u0002\u0010fJ\u0013\u0010g\u001a\u00020#2\b\u0010h\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010i\u001a\u00020jH\u00d6\u0001J\t\u0010k\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010(R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010(R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010(R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010(R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00103R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00103R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010-R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010-R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010-R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010-R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010-R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010(R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010(R\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\n\n\u0002\u0010A\u001a\u0004\b?\u0010@R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\n\n\u0002\u0010A\u001a\u0004\bB\u0010@R\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u00a2\u0006\n\n\u0002\u0010A\u001a\u0004\bC\u0010@R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010-R\u0013\u0010 \u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010(R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010(R\u0011\u0010\"\u001a\u00020#\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0011\u0010$\u001a\u00020#\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010H\u00a8\u0006n"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug;", "", "drugId", "", "nameTr", "nameEn", "genericName", "brandNames", "", "category", "atcCode", "drugClass", "mechanismTr", "adultDoses", "", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/DoseInfo;", "pediatricDoses", "specialPopulations", "pharmacokinetics", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/PharmacokineticsInfo;", "contraindications", "warnings", "sideEffectsCommon", "sideEffectsSerious", "clinicalPearls", "storage", "preparation", "maxMgkgWithEpi", "", "maxMgkgWithoutEpi", "absoluteMaxMg", "sources", "lastReviewed", "reviewer", "requiresClinicalValidation", "", "isPremium", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/PharmacokineticsInfo;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getDrugId", "()Ljava/lang/String;", "getNameTr", "getNameEn", "getGenericName", "getBrandNames", "()Ljava/util/List;", "getCategory", "getAtcCode", "getDrugClass", "getMechanismTr", "getAdultDoses", "()Ljava/util/Map;", "getPediatricDoses", "getSpecialPopulations", "getPharmacokinetics", "()Lcom/anesthesiaclinic/anesthesiabriefs/data/model/PharmacokineticsInfo;", "getContraindications", "getWarnings", "getSideEffectsCommon", "getSideEffectsSerious", "getClinicalPearls", "getStorage", "getPreparation", "getMaxMgkgWithEpi", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getMaxMgkgWithoutEpi", "getAbsoluteMaxMg", "getSources", "getLastReviewed", "getReviewer", "getRequiresClinicalValidation", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/anesthesiaclinic/anesthesiabriefs/data/model/PharmacokineticsInfo;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZ)Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug;", "equals", "other", "hashCode", "", "toString", "$serializer", "Companion", "app_debug"})
public final class Drug {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String drugId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nameTr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nameEn = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String genericName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> brandNames = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String category = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String atcCode = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String drugClass = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String mechanismTr = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> adultDoses = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> pediatricDoses = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.String> specialPopulations = null;
    @org.jetbrains.annotations.Nullable()
    private final com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo pharmacokinetics = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> contraindications = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> warnings = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> sideEffectsCommon = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> sideEffectsSerious = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> clinicalPearls = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String storage = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String preparation = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxMgkgWithEpi = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double maxMgkgWithoutEpi = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double absoluteMaxMg = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> sources = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastReviewed = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String reviewer = null;
    private final boolean requiresClinicalValidation = false;
    private final boolean isPremium = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.anesthesiaclinic.anesthesiabriefs.data.model.Drug.Companion Companion = null;
    
    public Drug(@org.jetbrains.annotations.NotNull()
    java.lang.String drugId, @org.jetbrains.annotations.NotNull()
    java.lang.String nameTr, @org.jetbrains.annotations.NotNull()
    java.lang.String nameEn, @org.jetbrains.annotations.NotNull()
    java.lang.String genericName, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> brandNames, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.String atcCode, @org.jetbrains.annotations.Nullable()
    java.lang.String drugClass, @org.jetbrains.annotations.Nullable()
    java.lang.String mechanismTr, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> adultDoses, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> pediatricDoses, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> specialPopulations, @org.jetbrains.annotations.Nullable()
    com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo pharmacokinetics, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> contraindications, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> warnings, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sideEffectsCommon, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sideEffectsSerious, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> clinicalPearls, @org.jetbrains.annotations.Nullable()
    java.lang.String storage, @org.jetbrains.annotations.Nullable()
    java.lang.String preparation, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMgkgWithEpi, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMgkgWithoutEpi, @org.jetbrains.annotations.Nullable()
    java.lang.Double absoluteMaxMg, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sources, @org.jetbrains.annotations.Nullable()
    java.lang.String lastReviewed, @org.jetbrains.annotations.Nullable()
    java.lang.String reviewer, boolean requiresClinicalValidation, boolean isPremium) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDrugId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNameTr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNameEn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGenericName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getBrandNames() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAtcCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDrugClass() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMechanismTr() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> getAdultDoses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> getPediatricDoses() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> getSpecialPopulations() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo getPharmacokinetics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getContraindications() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getWarnings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSideEffectsCommon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSideEffectsSerious() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getClinicalPearls() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStorage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPreparation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxMgkgWithEpi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getMaxMgkgWithoutEpi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getAbsoluteMaxMg() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSources() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastReviewed() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getReviewer() {
        return null;
    }
    
    public final boolean getRequiresClinicalValidation() {
        return false;
    }
    
    public final boolean isPremium() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component21() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component22() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component26() {
        return null;
    }
    
    public final boolean component27() {
        return false;
    }
    
    public final boolean component28() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.anesthesiaclinic.anesthesiabriefs.data.model.Drug copy(@org.jetbrains.annotations.NotNull()
    java.lang.String drugId, @org.jetbrains.annotations.NotNull()
    java.lang.String nameTr, @org.jetbrains.annotations.NotNull()
    java.lang.String nameEn, @org.jetbrains.annotations.NotNull()
    java.lang.String genericName, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> brandNames, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.Nullable()
    java.lang.String atcCode, @org.jetbrains.annotations.Nullable()
    java.lang.String drugClass, @org.jetbrains.annotations.Nullable()
    java.lang.String mechanismTr, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> adultDoses, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.anesthesiaclinic.anesthesiabriefs.data.model.DoseInfo> pediatricDoses, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> specialPopulations, @org.jetbrains.annotations.Nullable()
    com.anesthesiaclinic.anesthesiabriefs.data.model.PharmacokineticsInfo pharmacokinetics, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> contraindications, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> warnings, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sideEffectsCommon, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sideEffectsSerious, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> clinicalPearls, @org.jetbrains.annotations.Nullable()
    java.lang.String storage, @org.jetbrains.annotations.Nullable()
    java.lang.String preparation, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMgkgWithEpi, @org.jetbrains.annotations.Nullable()
    java.lang.Double maxMgkgWithoutEpi, @org.jetbrains.annotations.Nullable()
    java.lang.Double absoluteMaxMg, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> sources, @org.jetbrains.annotations.Nullable()
    java.lang.String lastReviewed, @org.jetbrains.annotations.Nullable()
    java.lang.String reviewer, boolean requiresClinicalValidation, boolean isPremium) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"com/anesthesiaclinic/anesthesiabriefs/data/model/Drug.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"})
    @java.lang.Deprecated()
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> {
        @org.jetbrains.annotations.NotNull()
        public static final com.anesthesiaclinic.anesthesiabriefs.data.model.Drug.$serializer INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final kotlinx.serialization.descriptors.SerialDescriptor descriptor = null;
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public final com.anesthesiaclinic.anesthesiabriefs.data.model.Drug deserialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @java.lang.Override()
        public final void serialize(@org.jetbrains.annotations.NotNull()
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull()
        com.anesthesiaclinic.anesthesiabriefs.data.model.Drug value) {
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
    
    @kotlin.Metadata(mv = {2, 0, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2 = {"Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/anesthesiaclinic/anesthesiabriefs/data/model/Drug;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final kotlinx.serialization.KSerializer<com.anesthesiaclinic.anesthesiabriefs.data.model.Drug> serializer() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}