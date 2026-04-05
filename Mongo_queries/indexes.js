db.customer_activity_logs.createIndex({ accountId: 1 });
db.customer_activity_logs.createIndex({ createdAt: -1 });

db.support_tickets.createIndex({ accountId: 1 });
db.support_tickets.createIndex({ status: 1 });