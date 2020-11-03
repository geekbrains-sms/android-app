package com.geekbrains.geekbrainsprogect.data.mapper.contract;



import com.geekbrains.geekbrainsprogect.data.api.dto.ProductTransactionDTO;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransaction;
import com.geekbrains.geekbrainsprogect.data.model.entity.ProductTransactionData;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.domain.model.ProductTransactionModel;

import java.util.List;

public interface ProductTransactionMapper extends BaseMapper<ProductTransactionDTO, ProductTransactionData, ProductTransactionModel, IProductTransactions> {
    List<ProductTransaction>toEntityListProductTransaction(List<? extends IProductTransactions>list);
}
