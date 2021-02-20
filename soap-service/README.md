# Payment Web Service (SOAP)

In order to test `issuePayment` go into `requests` and run following:

```bash
curl --header "content-type: text/xml" -d @issuePayment.xml http://localhost:8080/ws
```

In order to test `settlePayment` go into `requests` and run following:

```bash
curl --header "content-type: text/xml" -d @settlePayment.xml http://localhost:8080/ws
```

NOTE: Remember to change `transactionId` in `settlePayment.xml`.

In order to test `getUserPayments` go into `requests` and run following:

```bash
curl --header "content-type: text/xml" -d @getUserPayments.xml http://localhost:8080/ws
```