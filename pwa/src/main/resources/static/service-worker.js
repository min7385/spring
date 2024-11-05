self.addEventListener('push', event => {
const data = event.data.json();

    const options = {
        body: data.body,
        icon: '/icon-96x96.png',
        badge: '/icon-72x72.svg'
    };

    event.waitUntil(
        self.registration.showNotification(data.title, options)
    );
});

self.addEventListener('notificationclick', event => {
    event.notification.close();
    event.waitUntil(
        clients.openWindow('https://your-site-url.com')
    );
});