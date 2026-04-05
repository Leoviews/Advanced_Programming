db.support_tickets.aggregate([
  {
    $group: {
      _id: "$status",
      total: { $sum: 1 }
    }
  }
]);

db.customer_activity_logs.aggregate([
  {
    $group: {
      _id: "$action",
      total: { $sum: 1 }
    }
  }
]);