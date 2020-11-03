package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;

import java.text.DecimalFormat;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;
@Entity(tableName = "product_transaction")
public class ProductTransaction {
    @PrimaryKey
    @ColumnInfo(name = "transactionId", index = true)
    public long id;
    @ColumnInfo(name = "product_id")
    public long productId;
    @ColumnInfo(name = "contractor_id")
    public long contractorId;
    @ColumnInfo(name = "user_id")
    public long userId;
    String date;
    public double quantity;
    public String comment;


    public ProductTransaction(long id,long productId, long contractorId,long userId, String date, double quantity, String comment) {
        this.id = id;
        this.productId = productId;
        this.contractorId = contractorId;
        this.userId = userId;
        this.quantity = quantity;
        this.comment = comment;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public double getQuantity() {
        return quantity;
    }
    public String getStringQuantity()
    {
        return new DecimalFormat("#.##").format(quantity);
    }
    public String getDate() {
         return date;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getProductId() {
        return productId;
    }

    public long getContractorId() {
        return contractorId;
    }
}
