package com.anesthesiaclinic.anesthesiabriefs.data.repository

import com.anesthesiaclinic.anesthesiabriefs.data.model.Drug

object SeedDataDrugs {
    val drugsList = SeedDataDrugsPart1.drugsList +
                    SeedDataDrugsPart2.drugsList +
                    SeedDataDrugsPart3.drugsList +
                    SeedDataDrugsPart4.drugsList +
                    SeedDataDrugsPart5.drugsList
}
