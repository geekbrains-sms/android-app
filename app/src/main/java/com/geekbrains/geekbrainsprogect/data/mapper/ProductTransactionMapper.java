package com.geekbrains.geekbrainsprogect.data.mapper;



import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

public interface ProductTransactionMapper extends BaseMapper<ProductTransactionDTO, ProductTransaction, ProductTransactionModel, IProductTransactions> {
}
