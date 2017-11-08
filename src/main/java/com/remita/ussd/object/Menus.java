package com.remita.ussd.object;

import com.remita.ussd.dao.PushRequest;

public class Menus {


    public PushRequest processTransfer(PushRequest request, int step){

        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Transfer \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Transfer \n\n Select Recipient Bank: \n 1> United Bank of Africa \n 2> Access Bank \n 3> Akunna Matata Bank \n\n 0> Back ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Transfer \n\n Select Your Account To Debit Bank: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            }case 4:{
                request.setUssdContent(" Remita - Transfer \n\n Enter DAN: \n\n 0> Back ");
                break;
            }case 5:{
                request.setUssdContent(" Remita - Transfer \n\n Successful. \n Your new account balance is #300,000. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processAirtime(PushRequest request, int step){

        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Airtime \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Airtime \n\n Please Enter Telephone no: \n\n 0> Back  ");
                break;
            case 3:
                request.setUssdContent(" Remita - Airtime \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 4:{
                request.setUssdContent(" Remita - Airtime \n\n Successful. \n Your new account balance is #59,200. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processBalance(PushRequest request, int step){

        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Balance \n\n Select Your Account: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Balance \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Balance \n\n Your balance is #59,200. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processPayTSAAndBillers(PushRequest request, int step){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Enter RRR: \n\n 0> Back  ");
                break;
            case 2:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            case 3:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Select Your Account To Debit Bank: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            case 4:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 5:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Successful. \n Your just paid the sum of #59,200 to FAAN. \n\n 0> Back ");
                break;
            default:
        }

        return request;
    }

    public PushRequest processGetLoan(PushRequest request, int step){

        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Get Loan \n\n Your eligible loan is Nxxx. T&Cs apply: 3% Flat Interest; 2% Fee & 0.15% Credit Life Insurance. \n Please Enter Amount:  \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Get Loan \n\n Select Your Account To Be Credited: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Get Loan \n\n Successful. \n The sum of #30,000 has been credited to your account. It will be debited from your account on your next pay day. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processRRR(PushRequest request, int step){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - RRR \n\n Please Select A Function: \n 1> Pay RRR \n 2> Check Status \n\n 0> Back  ");
                break;
            case 2:
                request.setUssdContent(" Remita - RRR \n\n Enter RRR: \n\n 0> Back  ");
                break;
            case 3:
                request.setUssdContent(" Remita - RRR \n\n Please Enter Amount: \n\n 0> Back ");
                break;
            case 4:
                request.setUssdContent(" Remita - RRR \n\n Select Your Account To Debit Bank: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Back ");
                break;
            case 5:
                request.setUssdContent(" Remita - RRR \n\n Enter DAN: \n\n 0> Back  ");
                break;
            case 6:
                request.setUssdContent(" Remita - RRR \n\n Successful. \n Your just paid the sum of #59,200 to FAAN. \n\n 0> Back ");
                break;
            default:
        }

        return request;
    }

    public PushRequest processReceipt(PushRequest request, int step){

        switch (step){

            case 1:{
                request.setUssdContent(" Remita - Receipt \n\n Please Enter RRR: \n\n 0> Back ");
                break;
            }
            case 2:
                request.setUssdContent(" Remita - Receipt \n\n Enter Receiving Email: \n\n 0> Back  ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Receipt \n\n Receipt sent. \n\n 0> Back ");
                break;
            }
            default:
        }

        return request;
    }

    public PushRequest processRegistration(PushRequest request, int step){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Registration \n\n Please Enter Your BVN: \n\n 0> Back ");
                break;
            case 2:
                request.setUssdContent(" Remita - Registration \n\n Please Enter Your Telephone no: \n\n 0> Back ");
                break;
            case 3:
                request.setUssdContent(" Remita - Registration \n\n Please Confirm Your Details; \n ALOZIE MICHAEL \n 4/OCT/2001 \n 0814***3838: \n\n 1> YES 2> NO \n\n 0> Back ");
                break;
            case 4:
                request.setUssdContent(" Remita - Registration \n\n Add an account for your Remita profile. \n Enter Your Account Number: \n\n 0> Back  ");
                break;
            case 5:
                request.setUssdContent(" Remita - Registration \n\n Select Your Bank. \n 1> United Bank of Africa \n 2> Access Bank \n 3> Akunna Matata Bank \n\n 0> Back  ");
                break;
            case 6:
                request.setUssdContent(" Remita - Registration \n\n Please Enter OTP Sent To Your Telephone no: \n\n 0> Back  ");
                break;
            case 7:
                request.setUssdContent(" Remita - Registration \n\n Please choose your Dynamic Authentication Number (DAN). This should be a 2 to 4 digit number: \n\n 0> Back  ");
                break;
            case 8:
                request.setUssdContent(" Remita - Registration \n\n Successful. \n\n 0> Back ");
                break;
            default:
        }

        return request;
    }
}