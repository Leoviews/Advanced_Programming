db.customer_activity_logs.find();

db.customer_activity_logs.updateOne(
  { accountId: NumberLong(1) },
  { $set: { status: "FAILED" } }
);

db.customer_activity_logs.deleteOne({
  accountId: NumberLong(1)
});