package com.anesthesiaclinic.anesthesiabriefs

import com.anesthesiaclinic.anesthesiabriefs.utils.MedicalCalculators
import org.junit.Assert.assertEquals
import org.junit.Test

class MedicalCalculatorsTest {

    @Test
    fun testCalculateBMI() {
        // Weight: 70kg, Height: 175cm -> BMI: 70 / 1.75^2 = 22.857...
        val bmi = MedicalCalculators.calculateBMI(70.0, 175.0)
        assertEquals(22.86, bmi, 0.01)

        // Empty state / invalid inputs
        assertEquals(0.0, MedicalCalculators.calculateBMI(-10.0, 170.0), 0.001)
        assertEquals(0.0, MedicalCalculators.calculateBMI(70.0, 0.0), 0.001)
    }

    @Test
    fun testGetCategoryFromBMI() {
        assertEquals("Zayıf (Underweight)", MedicalCalculators.getCategoryFromBMI(17.5))
        assertEquals("Normal Kilolu (Normal)", MedicalCalculators.getCategoryFromBMI(22.0))
        assertEquals("Fazla Kilolu (Overweight)", MedicalCalculators.getCategoryFromBMI(27.5))
        assertEquals("Sınıf I Obez (Obese Class I)", MedicalCalculators.getCategoryFromBMI(32.0))
        assertEquals("Sınıf II Obez (Obese Class II)", MedicalCalculators.getCategoryFromBMI(37.0))
        assertEquals("Sınıf III Morbid Obez (Obese Class III)", MedicalCalculators.getCategoryFromBMI(42.0))
    }

    @Test
    fun testCalculateMAP() {
        // SBP: 120, DBP: 80 -> MAP: (120 + 160) / 3 = 93.33...
        val map = MedicalCalculators.calculateMAP(120.0, 80.0)
        assertEquals(93.33, map, 0.01)

        // Invalid inputs where SBP <= DBP
        assertEquals(0.0, MedicalCalculators.calculateMAP(80.0, 90.0), 0.001)
        assertEquals(0.0, MedicalCalculators.calculateMAP(-10.0, 80.0), 0.001)
    }

    @Test
    fun testCalculateBSA() {
        // Weight: 70kg, Height: 170cm -> BSA = sqrt( (70 * 170) / 3600 ) = sqrt(3.3055...) = 1.818...
        val bsa = MedicalCalculators.calculateBSA(70.0, 170.0)
        assertEquals(1.82, bsa, 0.01)
    }

    @Test
    fun testCalculateIBW() {
        // Male: 50 + 0.9 * (175 - 152.4) = 50 + 20.34 = 70.34
        val ibwMale = MedicalCalculators.calculateIBW("male", 175.0)
        assertEquals(70.34, ibwMale, 0.01)

        // Female: 45.5 + 0.9 * (160 - 152.4) = 45.5 + 6.84 = 52.34
        val ibwFemale = MedicalCalculators.calculateIBW("female", 160.0)
        assertEquals(52.34, ibwFemale, 0.01)
    }

    @Test
    fun testCalculateEBV() {
        // Adult male: 70 mL/kg. 80 kg -> 5600 mL
        val ebv = MedicalCalculators.calculateEBV(80.0, "adult_male")
        assertEquals(5600.0, ebv, 0.1)

        // Adult female: 65 mL/kg. 60 kg -> 3900 mL
        val ebvFemale = MedicalCalculators.calculateEBV(60.0, "adult_female")
        assertEquals(3900.0, ebvFemale, 0.1)
    }

    @Test
    fun testCalculateAllowableBloodLoss() {
        // EBV: 5000 mL, Initial Hb: 14.0, Lowest Hb: 9.0 -> ABL = 5000 * (14.0 - 9.0) / 14.0 = 1785.7...
        val abl = MedicalCalculators.calculateAllowableBloodLoss(5000.0, 14.0, 9.0)
        assertEquals(1785.71, abl, 0.01)
    }

    @Test
    fun testCalculateMaintenanceFluid421() {
        // 5 kg -> 5 * 4 = 20
        assertEquals(20.0, MedicalCalculators.calculateMaintenanceFluid421(5.0), 0.1)
        // 15 kg -> 40 + 5 * 2 = 50
        assertEquals(50.0, MedicalCalculators.calculateMaintenanceFluid421(15.0), 0.1)
        // 70 kg -> 60 + 50 * 1 = 110
        assertEquals(110.0, MedicalCalculators.calculateMaintenanceFluid421(70.0), 0.1)
    }

    @Test
    fun testCalculateNpoSafety() {
        // Clear liquid after 3 hours (suitable)
        val result1 = MedicalCalculators.calculateNpoSafety("clear_liquid", 8, 0, 11, 0, "adult", false)
        assertEquals("suitable", result1.status)
        assertEquals(3.0, result1.elapsedHours, 0.01)
        assertEquals(0.0, result1.remainingHours, 0.01)

        // Light meal after 4 hours (wait, needs 6 hours)
        val result2 = MedicalCalculators.calculateNpoSafety("light_meal", 8, 0, 12, 0, "adult", false)
        assertEquals("wait", result2.status)
        assertEquals(4.0, result2.elapsedHours, 0.01)
        assertEquals(2.0, result2.remainingHours, 0.01)

        // Clear liquid after 3 hours but high risk patient flag (high_risk_review_required)
        val result3 = MedicalCalculators.calculateNpoSafety("clear_liquid", 8, 0, 11, 0, "adult", true)
        assertEquals("high_risk_review_required", result3.status)
    }

    @Test
    fun testScoreDifficultAirway() {
        // Low risk patient
        val result1 = MedicalCalculators.scoreDifficultAirway(
            mallampati = 1,
            mouthOpeningCm = 4.5,
            thyromentalDistanceCm = 7.0,
            neckMobilityLimited = false,
            upperLipBiteClass = 1,
            prevDifficultAirway = false,
            hasOsa = false,
            isObese = false,
            hasBeard = false,
            isEdentulous = false,
            hasCervicalLimit = false,
            isPregnant = false,
            hasAirwayPathology = false
        )
        assertEquals("low", result1.riskLevel)

        // High risk patient (Mallampati 4 or mouth opening < 3)
        val result2 = MedicalCalculators.scoreDifficultAirway(
            mallampati = 4,
            mouthOpeningCm = 2.5,
            thyromentalDistanceCm = 5.5,
            neckMobilityLimited = true,
            upperLipBiteClass = 3,
            prevDifficultAirway = true,
            hasOsa = true,
            isObese = true,
            hasBeard = true,
            isEdentulous = true,
            hasCervicalLimit = true,
            isPregnant = true,
            hasAirwayPathology = true
        )
        assertEquals("high", result2.riskLevel)
    }

    @Test
    fun testCalculatePediatricDoses() {
        val doses = MedicalCalculators.calculatePediatricDoses(10.0)
        assertEquals(0.1, doses.adrenalinMg, 0.001)
        assertEquals(1.0, doses.adrenalinMl, 0.01)
        assertEquals(0.2, doses.atropinMg, 0.01)
        assertEquals(2.0, doses.atropinMl, 0.01)
        assertEquals(25.0, doses.propofolMg, 0.1)
        assertEquals(2.5, doses.propofolMl, 0.1)
        assertEquals(20.0, doses.ketaminMg, 0.1)
        assertEquals(2.0, doses.ketaminMl, 0.1)
        assertEquals(15.0, doses.fentanylMcg, 0.1)
        assertEquals(0.3, doses.fentanylMl, 0.05)
        assertEquals(6.0, doses.rocuroniumMg, 0.1)
        assertEquals(0.6, doses.rocuroniumMl, 0.05)
        assertEquals(15.0, doses.succinylcholineMg, 0.1)
        assertEquals(0.75, doses.succinylcholineMl, 0.05)
    }

    @Test
    fun testCalculateInfusionRate() {
        // Noradrenaline: 70kg, 4mg syringe in 50mL, target dose 0.1 mcg/kg/min -> 5.25 mL/hour
        val rateNor = MedicalCalculators.calculateInfusionRate("noradrenalin", 70.0, 4.0, 50.0, 0.1)
        assertEquals(5.25, rateNor, 0.01)

        // Nitroglycerin: 70kg, 10mg syringe in 50mL, target dose 20 mcg/min -> 6.0 mL/hour
        val rateNtg = MedicalCalculators.calculateInfusionRate("nitrogliserin", 70.0, 10.0, 50.0, 20.0)
        assertEquals(6.0, rateNtg, 0.01)
    }

    @Test
    fun testCalculateDantroleneRescue() {
        // Dantrolene: 70kg -> bolus 175mg, 9 vials, 540mL sterile water
        val dantrolene = MedicalCalculators.calculateDantroleneRescue(70.0)
        assertEquals(175.0, dantrolene.bolusMg, 0.1)
        assertEquals(9, dantrolene.totalVials)
        assertEquals(540.0, dantrolene.sterileWaterMl, 0.1)
    }

    @Test
    fun testCalculateLipidRescue() {
        // Lipid: 70kg -> bolus 105mL, infusion 17.5 mL/min
        val lipid = MedicalCalculators.calculateLipidRescue(70.0)
        assertEquals(105.0, lipid.first, 0.1)
        assertEquals(17.5, lipid.second, 0.1)
    }

    @Test
    fun testUnitConversions() {
        // 70 kg in pounds -> 70 * 2.20462 = 154.3234
        val displayWeight = 70.0 * 2.20462
        assertEquals(154.32, displayWeight, 0.01)

        // 150 lbs in kg -> 150 / 2.20462 = 68.038...
        val internalWeight = 150.0 / 2.20462
        assertEquals(68.04, internalWeight, 0.01)

        // 170 cm in inches -> 170 / 2.54 = 66.929...
        val displayHeight = 170.0 / 2.54
        assertEquals(66.93, displayHeight, 0.01)

        // 60 inches in cm -> 60 * 2.54 = 152.4
        val internalHeight = 60.0 * 2.54
        assertEquals(152.4, internalHeight, 0.001)
    }
}

