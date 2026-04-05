for (let i = 1; i <= 100; i++) {
  db.customer_activity_logs.insertOne({
    accountId: NumberLong(i),
    action: "Login",
    status: "SUCCESS",
    createdAt: new Date()
  });

  db.support_tickets.insertOne({
    accountId: NumberLong(i),
    subject: "ATM issue " + i,
    status: "OPEN",
    createdAt: new Date()
  });
}