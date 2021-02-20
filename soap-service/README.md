# Payment Web Service (SOAP)

In order to test `issuePayment` run following:

```bash
curl --header "content-type: text/xml" -d @issuePayment.xml http://localhost:8080/ws
```

In order to test `settlePayment` run following:

```bash
curl --header "content-type: text/xml" -d @settlePayment.xml http://localhost:8080/ws
```

NOTE: Remember to change `transactionId` in `settlePayment.xml`.
