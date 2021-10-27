import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from "router/router"
import App from 'pages/App.vue'
import store from "./srore/store"
import {connect} from "./util/ws"
import 'vuetify/dist/vuetify.min.css'
import * as Sentry from '@sentry/browser'
import * as Integrations from '@sentry/integrations'

Sentry.init({
    dsn: 'https://a83eac94e4114b599b57e2a962a6fcde@o1052451.ingest.sentry.io/6036301',
    integrations: [
        new Integrations.Vue({
            Vue,
            attachProps: true,
        }),
    ],
})

Sentry.configureScope(scope =>
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name
    })
)


if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App),
    vuetify: new Vuetify({}),
})
