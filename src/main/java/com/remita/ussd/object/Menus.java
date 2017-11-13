package com.remita.ussd.object;

import com.remita.ussd.dao.PushRequest;
import com.remita.ussd.model.Activities;
import com.remita.ussd.model.AirtimeTransactions;
import com.remita.ussd.model.BillerTransactions;
import com.remita.ussd.model.TransferTransactions;
import com.remita.ussd.repository.ActivitiesRepository;
import com.remita.ussd.repository.AirtimeTransactionsRepository;
import com.remita.ussd.repository.BillerTransactionsRepository;
import com.remita.ussd.repository.TransferTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Menus {

    @Autowired
    ActivitiesRepository activitiesRepository;

    @Autowired
    TransferTransactionsRepository transferTransactionsRepository;

    @Autowired
    AirtimeTransactionsRepository airtimeTransactionsRepository;

    @Autowired
    BillerTransactionsRepository billerTransactionsRepository;


    public PushRequest processTransfer(PushRequest request, Operation operation, String ussdContent){

        switch (operation.getStep()){

            case 1:
                request.setUssdContent(" Remita - Transfer \n\n Enter amount: \n\n 0> Home ");
                break;
            case 2: {
                //operation.getMetaData().putIfAbsent("amount", ussdContent);
                request.setUssdContent(" Remita - Transfer \n\n Enter recipient account: \n\n 0> Home ");
                break;
            }
            case 3:
                request.setUssdContent(" Remita - Transfer \n\n Select recipient bank: \n 1> United Bank of Africa \n 2> Access Bank \n 3> Akunna Matata Bank \n\n 0> Home ");
                break;
            case 4:
                request.setUssdContent(" Remita - Transfer \n\n Select your account to debit: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Home ");
                break;
            case 5:
                request.setUssdContent(" Remita - Transfer \n\n Enter PIN: \n\n 0> Home ");
                break;
            case 6: {
                request.setUssdContent(" Remita - Transfer \n\n Successful. \n Your new account balance is #300,000. \n\n 0> Home ");
                break;
            }
            default:
                request.setUssdContent(" Remita - Transfer \n\n Invalid response. \n\n 0> Home ");
                break;
        }

        return request;
    }

    public PushRequest processAirtime(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Airtime \n\n Enter amount: \n\n 0> Home ");
                break;
            case 2:
                request.setUssdContent(" Remita - Airtime \n\n Enter telephone no: \n\n 0> Home  ");
                break;
            case 3:
                request.setUssdContent(" Remita - Airtime \n\n Enter PIN: \n\n 0> Home  ");
                break;
            case 4: {
                request.setUssdContent(" Remita - Airtime \n\n Successful. \n Your new account balance is #59,200. \n\n 0> Home ");
                break;
            }
            default:
                request.setUssdContent(" Remita - Airtime \n\n Invalid response. \n\n 0> Home ");
                break;
        }
        return request;
    }

    public PushRequest processBalance(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Balance \n\n Select your account to view: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Home ");
                break;
            case 2:
                request.setUssdContent(" Remita - Balance \n\n Enter PIN: \n\n 0> Home  ");
                break;
            case 3:{
                request.setUssdContent(" Remita - Balance \n\n Your balance on your 123***123/UBA is #59,200. \n\n 0> Home ");
                break;
            }
            default:
                request.setUssdContent(" Remita - Balance \n\n Invalid response. \n\n 0> Home ");
                break;
        }
        return request;
    }

    public PushRequest processPayTSAAndBillers(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Enter RRR: \n\n 0> Home  ");
                break;
            case 2:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n RRR: 123421343 \n Type: Invoice \n Amount: #10,000 \n Biller: NCC \n Payer: Alozie Michael \n Telephone: 08146963838 \n Status: pending \n\n 1> Pay \n\n 0> Home ");
                break;
            case 3:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Select your account to debit: \n 1> 123***123/UBA \n 2> 345***999/FBN \n\n 0> Home ");
                break;
            case 4:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Enter PIN: \n\n 0> Home  ");
                break;
            case 5: {
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Successful. \n You just paid the sum of #59,200 to NCC. \n\n 0> Home ");
                break;
            }
            default:
                request.setUssdContent(" Remita - Pay TSA and Billers \n\n Invalid response. \n\n 0> Home ");
                break;
        }
        return request;
    }

    public PushRequest processGetLoan(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Get Loan \n\n I accept that my salary and transaction history can be accessed to evaluate my loan eligibility. Additional T&C at bit.ly/rmttc. \n Enter loan amount:  \n\n 0> Home ");
                break;
            case 2:
                request.setUssdContent(" Remita - Get Loan \n\n 1> Access Payday. N5,000 pay 6500 in 10 days \n 2> PayLater. N4,000 pay N5,600 in 5 days \n 3> Mo-De. N5,000 pay N5,600 in 5 days. \n\n 0> Home ");
                break;
            case 3:
                request.setUssdContent(" Remita - Get Loan \n\n Where do you want your loan to be credited? \n 1> 123***123/UBA \n 2> 345***999/FBN \n\n 0> Home ");
                break;
            case 4:
                request.setUssdContent(" Remita - Get Loan \n\n Successful. \n Enter PIN. \n\n 0> Home ");
                break;
            case 5:
                request.setUssdContent(" Remita - Get Loan \n\n Successful. \n Congratulations! Your loan has been approved and would be transferred to your 123***123/FBN account within the next 1 hour. \n\n 0> Home ");
                break;
            default:
                request.setUssdContent(" Remita - Get Loan \n\n Invalid response. \n\n 0> Home ");
                break;
        }
        return request;
    }

    public PushRequest processRRR(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - RRR \n\n Enter RRR: \n\n 0> Home  ");
                break;
            case 2:
                request.setUssdContent(" Remita - RRR \n\n RRR: 123421343 \n Type: Invoice \n Amount: #15,000 \n Biller: CAC \n Payer: Alozie Michael \n Telephone: 08146963838 \n Status: pending \n\n 1> Pay \n\n 0> Home ");
                break;
            case 3:
                request.setUssdContent(" Remita - RRR \n\n Select your account to debit: \n 1>123***123/UBA \n 2>345***999/FBN \n\n 0> Home ");
                break;
            case 4:
                request.setUssdContent(" Remita - RRR \n\n Enter PIN: \n\n 0> Home  ");
                break;
            case 5: {
                request.setUssdContent(" Remita - RRR \n\n Successful. \n You just paid the sum of #109,200 to Alozie and sons enterprise. \n\n 0> Home ");
                break;
            }
            default:
                request.setUssdContent(" Remita - RRR \n\n Invalid response. \n\n 0> Home ");
                break;
        }
        return request;
    }

    public PushRequest processReceipt(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Receipt \n\n Enter RRR: \n\n 0> Home ");
                break;
            case 2:
                request.setUssdContent(" Remita - Receipt \n\n 1> Resend Receipt \n 2> Send Receipt to Another Email \n\n 0> Home ");
                break;
            case 3:
                request.setUssdContent(" Remita - Receipt \n\n Enter receiving email: \n\n 0> Home  ");
                break;
            case 4:
                request.setUssdContent(" Remita - Receipt \n\n Receipt sent. \n\n 0> Home ");
                break;
            default:
                request.setUssdContent(" Remita - Receipt \n\n Invalid response. \n\n 0> Home ");
                break;
        }
        return request;
    }

    public PushRequest processRegistration(PushRequest request, int step, String ussdContent){

        switch (step){

            case 1:
                request.setUssdContent(" Remita - Registration \n\n Enter your BVN: \n\n 0> Home ");
                break;
            case 2:
                request.setUssdContent(" Remita - Registration \n\n Enter your telephone no: \n\n 0> Home ");
                break;
            case 3:
                request.setUssdContent(" Remita - Registration \n\n Please confirm your details; \n ALOZIE MICHAEL \n 4/OCT/2001 \n 0814***3838: \n\n 1> YES \n 2> NO \n\n 0> Home ");
                break;
            case 4:
                request.setUssdContent(" Remita - Registration \n\n Add an account to your Remita profile. \n Enter your account number: \n\n 0> Home  ");
                break;
            case 5:
                request.setUssdContent(" Remita - Registration \n\n Select your bank. \n 1> United Bank of Africa \n 2> Access Bank \n 3> Akunna Matata Bank \n\n 0> Home  ");
                break;
            case 6:
                request.setUssdContent(" Remita - Registration \n\n Your OTP has been sent to your phone. Dial *7000*8*6# and enter your OTP: \n\n 0> Home  ");
                break;
            case 7:
                request.setUssdContent(" Remita - Registration \n\n Successful. \n\n 0> Back ");
                break;
            default:
                request.setUssdContent(" Remita - Registration \n\n Invalid response. \n\n 0> Home ");
                break;
        }

        return request;
    }

    public void persistTransferTransactions(PushRequest pushRequest, Operation operation){

        String amount = operation.getMetaData().get("amount");

        String createdDate = new SimpleDateFormat("yyyy/MM/dd HH.ss").format(new Date());
        TransferTransactions transferTransactions = new TransferTransactions();
        Activities activities = activitiesRepository.findBySessionIDContaining(pushRequest.getSessionId());

        transferTransactions.setCreatedDate(createdDate);
        transferTransactions.setMsisdn(pushRequest.getMsisdn());
        transferTransactions.setDestinationTransactionStatus("successful");
        transferTransactions.setTransactionAmount(amount);
        transferTransactions.setActivities(activities);

        transferTransactionsRepository.save(transferTransactions);
    }

    public void persistAirtimeTransactions(PushRequest pushRequest){

        String createdDate = new SimpleDateFormat("yyyy/MM/dd HH.ss").format(new Date());
        AirtimeTransactions airtimeTransactions = new AirtimeTransactions();
        Activities activities = activitiesRepository.findBySessionIDContaining(pushRequest.getSessionId());

        airtimeTransactions.setCreatedDate(createdDate);
        airtimeTransactions.setMsisdn(pushRequest.getMsisdn());
        airtimeTransactions.setDestinationTransactionStatus("successful");
        airtimeTransactions.setActivities(activities);

        airtimeTransactionsRepository.save(airtimeTransactions);
    }

    public void persistBillerTransactions(PushRequest pushRequest){

        String createdDate = new SimpleDateFormat("yyyy/MM/dd HH.ss").format(new Date());
        BillerTransactions billerTransactions = new BillerTransactions();
        Activities activities = activitiesRepository.findBySessionIDContaining(pushRequest.getSessionId());

        billerTransactions.setCreatedDate(createdDate);
        billerTransactions.setTransactionAmount("20,000");
        billerTransactions.setBillerName("Federal university of technology, owerri");
        billerTransactions.setBillerServiceName("School fees");
        billerTransactions.setActivities(activities);

        billerTransactionsRepository.save(billerTransactions);
    }

}