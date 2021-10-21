package com.medicalshop.app.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Medicine.class,Company.class, Composition.class,MedicineComposition.class,Stock.class,Wholesaler.class,Demo.class},views = {MedicineWithCompanyName.class,MedicineCompositionWithMedicineName.class,MedicineCompositionWithName.class,StockWithWholesalerName.class,StockWithMedicineAndWholesalerName.class},version = 3)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicineDao medicineDao();
    public abstract CompanyDao companyDao();
    public abstract CompositionDao compositionDao();
    public abstract MedicineCompositionDao medicineCompositionDao();
    public abstract StockDao stockDao();
    public abstract WholesalerDao wholesalerDao();
    public abstract MedicineWithCompanyNameDao medicineWithCompanyNameDao();
    public abstract MedicineCompositionWithNameDao medicineCompositionWithNameDao();
    public abstract MedicineCompositionWithMedicineNameDao medicineCompositionWithMedicineNameDao();
    public abstract StockWithWholesalerNameDao stockWithWholesalerNameDao();
    public abstract StockWithMedicineAndWholesalerNameDao stockWithMedicineAndWholesalerNameDao();
    public abstract DemoDao demoDao();
    private static AppDatabase db;
    public  static AppDatabase getdb(Context context)
    {
        if(db == null)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    db= Room.databaseBuilder(context,AppDatabase.class,"Medicine.db").addMigrations(new Migration(1, 2) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            database.execSQL("alter table companies add contactNo2 text ");
                        }
                    }, new Migration(2,3) {
                        @Override
                        public void migrate(@NonNull SupportSQLiteDatabase database) {
                            database.execSQL("create table demo (id integer primary key AUTOINCREMENT  , name text )");
                        }
                    }).build();

                }
            }).start();
        }
        return db;
    }
}
