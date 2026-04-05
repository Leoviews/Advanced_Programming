db.support_tickets.aggregate([
  {
    $lookup: {
      from: "customer_activity_logs",
      localField: "accountId",
      foreignField: "accountId",
      as: "activity_logs"
    }
  }
]);

db.support_tickets.find().sort({ createdAt: -1 });

db.support_tickets.find(
  {},
  { subject: 1, status: 1 }
);