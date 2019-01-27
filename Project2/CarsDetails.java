package com.gupta.sudhanshu.cs478.sdmpproject2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class CarsDetails {

    String manufacturer;
    Integer image_path;
    String manufacturer_url;
    ArrayList<DealersDetails> dealers;

    public CarsDetails(){
        dealers = new ArrayList<DealersDetails>();
    }

    // Store car and dealer details together in ArrayList
    public static ArrayList<CarsDetails> createList(){
        final ArrayList<CarsDetails> carList = new ArrayList<>();

        //Images from res folder
        ArrayList<Integer> carImages = new ArrayList<Integer>(
                Arrays.asList(R.drawable.audi1, R.drawable.bmw,
                        R.drawable.ford, R.drawable.lamborghini, R.drawable.maserati,
                        R.drawable.rangerover, R.drawable.mustang, R.drawable.mercedes,
                        R.drawable.toyota));

        // car manufacturers
        ArrayList<String> carNames = new ArrayList<String>( Arrays.asList("Audi",
                "BMW",
                "Ford",
                "Lamborghini",
                "Maserati",
                "RangeRover",
                "Mustang",
                "Mercedes",
                "Toyota"));

        // manufacturer urls
        ArrayList<String> carUrls = new ArrayList<String>(Arrays.asList("https://www.audiusa.com",
                "https://www.bmwusa.com/",
                "https://www.ford.com/",
                "https://www.lamborghini.com/en-en",
                "https://www.maseratiusa.com/maserati/us/en",
                "https://www.landroverusa.com/vehicles/range-rover/index.html",
                "https://www.ford.com/cars/mustang/",
                "https://www.mbusa.com/en/home",
                "https://www.toyota.com/"));

        //detailes of car dealers' names
        ArrayList<ArrayList<String>> dealerName = new ArrayList<ArrayList<String>>();
        //Audi
        ArrayList<String> dn_audi = new ArrayList<String>(Arrays.asList("Fletcher Jones Audi","Audi Morton Grove","Audi Exchange"));
        //bmw
        ArrayList<String> dn_bmw = new ArrayList<String>(Arrays.asList("Perillo BMW","Bavarian Motors","Greater Chicago Motors"));
        //ford
        ArrayList<String> dn_ford = new ArrayList<String>(Arrays.asList("Bredemann Ford in Glenview","Fox Ford Lincoln","Metro Ford Sales and Service"));
        //lambo
        ArrayList<String> dn_lambo = new ArrayList<String>(Arrays.asList("Lamborghini Gold Coast Showroom","Chicago Motor Cars","Perillo Downers Grove"));
        //maserati
        ArrayList<String> dn_maserati = new ArrayList<String>(Arrays.asList("Fields Maserati","MASERATI OF CHICAGO","Chicago Motors Inc."));
        //landrover
        ArrayList<String> dn_land = new ArrayList<String>(Arrays.asList("Land Rover Chicago","Land Rover Hinsdale","Land Rover of Naperville"));
        //mustang
        ArrayList<String> dn_mustang = new ArrayList<String>(Arrays.asList("Fox Ford Lincoln","Gateway Classic Cars of Chicago","Greater Chicago Motors"));
        //mercedes
        ArrayList<String> dn_merc = new ArrayList<String>(Arrays.asList("Mercedes-Benz of Chicago","Loeber Motors","Mercedes-Benz of Westmont"));
        //toyota
        ArrayList<String> dn_toyota = new ArrayList<String>(Arrays.asList("Grossinger City Toyota","Chicago Northside Toyota","Toyota On Western"));

        dealerName.add(dn_audi);
        dealerName.add(dn_bmw);
        dealerName.add(dn_ford);
        dealerName.add(dn_lambo);
        dealerName.add(dn_maserati);
        dealerName.add(dn_land);
        dealerName.add(dn_mustang);
        dealerName.add(dn_merc);
        dealerName.add(dn_toyota);

        //details of car dealers' address
        ArrayList<ArrayList<String>> dealerAddress = new ArrayList<ArrayList<String>>();
        //audi
        ArrayList<String> da_audi = new ArrayList<String>(Arrays.asList("645 W Randolph St, Chicago, IL 60661","420 E Ogden Ave, Hinsdale, IL 60521","677 N Clark St, Chicago, IL 60654"));
        //bmw
        ArrayList<String> da_bmw = new ArrayList<String>(Arrays.asList("1035 N Clark St, Chicago, IL 60610","27 N May St, Chicago, IL 60607","1850 N Elston Ave, Chicago, IL 60642"));
        //ford
        ArrayList<String> da_ford = new ArrayList<String>(Arrays.asList("2038 Waukegan Rd, Glenview, IL 60025","2501 N Elston Ave, Chicago, IL 60647","6455 S Western Ave, Chicago, IL 60636"));
        //lambo
        ArrayList<String> da_lambo = new ArrayList<String>(Arrays.asList("834 N Rush St, Chicago, IL 60611","27W110 North Ave, West Chicago, IL 60185","330 Ogden Ave, Downers Grove, IL 60515"));
        //maserati
        ArrayList<String> da_maserati = new ArrayList<String>(Arrays.asList("1924 N Paulina St, Chicago, IL 60622","300 E Ogden Ave, Hinsdale, IL 60521","1559 W Ogden Ave, Suite A, Naperville, IL 60540"));
        //rangerover
        ArrayList<String> da_land = new ArrayList<String>(Arrays.asList("5133 W Irving Park Rd, Chicago, IL 60641","7340 S Western Ave, Chicago, IL 60636","7911 W Roosevelt Rd, Forest Park, IL 60130"));
        //mustang
        ArrayList<String> da_mustang = new ArrayList<String>(Arrays.asList("2501 N Elston Ave, Chicago, IL 60647","1329 Commerce Dr, Crete, IL 60417","2501 N Elston Ave, Chicago, IL 60647"));
        //mercedes
        ArrayList<String> da_merc = new ArrayList<String>(Arrays.asList("949 N Elston Ave Suite 2, Chicago, IL 60642","4255 Touhy Ave, Lincolnwood, IL 60712","200 E Ogden Ave, Westmont, IL 60559"));
        //toyota
        ArrayList<String> da_toyota = new ArrayList<String>(Arrays.asList("1561 N Fremont St, Chicago, IL 60642","6042 N Western Ave, Chicago, IL 60659","6941 S Western Ave, Chicago, IL 60636"));

        dealerAddress.add(da_audi);
        dealerAddress.add(da_bmw);
        dealerAddress.add(da_ford);
        dealerAddress.add(da_lambo);
        dealerAddress.add(da_maserati);
        dealerAddress.add(da_land);
        dealerAddress.add(da_mustang);
        dealerAddress.add(da_merc);
        dealerAddress.add(da_toyota);

        //details of car dealers' numbers
        ArrayList<ArrayList<String>> dealerNumber = new ArrayList<ArrayList<String>>();
        //audi
        ArrayList<String> dnu_audi = new ArrayList<String>(Arrays.asList("(312) 635-6482","(630) 480-41443 ","(312) 624-8586"));
        //bmw
        ArrayList<String> dnu_bmw = new ArrayList<String>(Arrays.asList("(312) 981-0000","(312) 455-8500","(312) 280-9262"));
        //ford
        ArrayList<String> dnu_ford = new ArrayList<String>(Arrays.asList("(847) 510-5585","(888) 380-8568","(773) 776-7600"));
        //lambo
        ArrayList<String> dnu_lambo = new ArrayList<String>(Arrays.asList("(312) 280-4848","(630) 221-1800","(630) 241-4848"));
        //maserati
        ArrayList<String> dnu_maserati = new ArrayList<String>(Arrays.asList("(847) 510-5585","(888) 380-8568","(773) 776-7600"));
        //rangerover
        ArrayList<String> dnu_land = new ArrayList<String>(Arrays.asList("(773) 777-2000","(773) 476-7800","(708) 366-1001"));
        //mustang
        ArrayList<String> dnu_mustang = new ArrayList<String>(Arrays.asList("(773) 687-7800","(708) 444-4488","(630) 300-5000"));
        //mercedes
        ArrayList<String> dnu_merc = new ArrayList<String>(Arrays.asList("(312) 628-4444","(847) 675-1000","(630) 537-0313"));
        //toyota
        ArrayList<String> dnu_toyota = new ArrayList<String>(Arrays.asList("(312) 256-8909","(773) 728-5000","(773) 884-7200"));

        dealerNumber.add(dnu_audi);
        dealerNumber.add(dnu_bmw);
        dealerNumber.add(dnu_ford);
        dealerNumber.add(dnu_lambo);
        dealerNumber.add(dnu_maserati);
        dealerNumber.add(dnu_land);
        dealerNumber.add(dnu_mustang);
        dealerNumber.add(dnu_merc);
        dealerNumber.add(dnu_toyota);


        // Create a carDetails object and bind it with the carDetails
        for(int i=0; i < carImages.size(); i++){

            CarsDetails cars = new CarsDetails();
            cars.image_path = carImages.get(i);
            cars.manufacturer = carNames.get(i);
            cars.manufacturer_url = carUrls.get(i);

            //for i-th car details and store in Car Details object
            for(int j=0; j<dealerName.get(i).size(); j++){
                DealersDetails dealer = new DealersDetails();
                dealer.name = dealerName.get(i).get(j);
                dealer.address = dealerAddress.get(i).get(j);
                dealer.number = dealerNumber.get(i).get(j);
                cars.dealers.add(j,dealer);
            }
            carList.add(cars);
        }

        return carList;
    }

    public static class DealersDetails{
        public String name;
        public String address;
        public String number;
    }
}
