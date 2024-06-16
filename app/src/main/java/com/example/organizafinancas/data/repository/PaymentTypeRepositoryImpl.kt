package com.example.organizafinancas.data.repository

import com.example.organizafinancas.data.source.local.PaymentTypeDao
import com.example.organizafinancas.domain.model.PaymentType
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentTypeRepositoryImpl @Inject constructor(
    private val dao: PaymentTypeDao
) : PaymentTypeRepository {

    override suspend fun fetchPaymentTypes() = dao.getAll().flowOn(IO)

//    override suspend fun fetchPaymentTypes(): Flow<List<PaymentType>> {
//            withContext(IO){
//                dao.insertAll(
//                    PaymentType(
//                        name = PaymentTypeEnum.CASH.paymentType,
//                        isSelected = true,
//                        initialDate = LocalDate.of(2024, 5, 1),
//                        finishDate = LocalDate.of(2024, 6, 30)
//                    ),
//                    PaymentType(
//                        name = PaymentTypeEnum.CREDIT.paymentType,
//                        isSelected = true,
//                        initialDate = LocalDate.of(2024, 5, 1),
//                        finishDate = LocalDate.of(2024, 6, 30)
//                    ),
//                )
//        }
//        return dao.getAll().flowOn(IO)
//    }

    override suspend fun savePaymentType(paymentType: PaymentType) {
        withContext(IO) {
            dao.insertAll(paymentType)
        }
    }
}