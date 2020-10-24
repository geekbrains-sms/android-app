package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.geekbrains.geekbrainsprogect.data.model.interf.IProduct;
import com.geekbrains.geekbrainsprogect.data.model.interf.IProductTransactions;
import com.geekbrains.geekbrainsprogect.data.model.interf.IUser;

import java.text.DecimalFormat;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;
@Entity(tableName = "product_transaction")
public class ProductTransaction implements IProductTransactions {
    @PrimaryKey
    @ColumnInfo(name = "transactionId")
    public long id;
    @ColumnInfo(name = "productId")
    @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id", onDelete = CASCADE)
    public long productId;
    @ColumnInfo(name = "contractorId")
    @ForeignKey(entity = Contractor.class, parentColumns = "id", childColumns = "contractor_id")
    public long contractorId;
    @ColumnInfo(name = "userId")
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")
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

    @Override
    public Contractor getContractor() {
        return null;
    }

    @Override
    public IUser getUser() {
        return null;
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

    @Override
    public IProduct getProduct() {
        return null;
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
