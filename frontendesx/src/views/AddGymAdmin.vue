<template>
  <nav>
    <v-toolbar flat app>
      <v-toolbar-side-icon @click="drawer = !drawer" class="grey--text">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-toolbar-side-icon>
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light">Admin</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat color="grey" @click="$router.push({ name: 'loginAdmin' })">
        <span>Sign Out</span>
        <v-icon right>mdi-export</v-icon>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer app v-model="drawer" class="grey">
      <v-list-item-group
        v-model="group"
        active-class="deep-purple--text text--accent-5"
      >
        <v-list-item
          v-for="link in links"
          :key="link.text"
          router
          :to="link.route"
        >
          <v-list-tile-action>
            <v-icon class="black--text">{{ link.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title class="white--text">{{
              link.text
            }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-item>
      </v-list-item-group>
    </v-navigation-drawer>

    <v-form ref="form" class="ml-6 mr-6">
      <v-col lg="8">
        <v-text-field v-model="name" label="Name Gym" required></v-text-field>

        <v-text-field v-model="adress" label="Adress" required></v-text-field>

        <v-btn
          color="success"
          class="mr-4"
          @click="$router.push({ name: 'gymsAdmin' })"
        >
          Back
        </v-btn>
        <v-btn color="success" class="mr-4" @click="addGym"> Add Gym </v-btn>
      </v-col>
    </v-form>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "AddNewUser",
  data() {
    return {
      name: "",
      adress: "",
      dialog: false,
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/homeAdmin" },
        { icon: "mdi-account-multiple", text: "Users", route: "/admin/users" },
        {
          icon: "mdi-clipboard-text",
          text: "Subscriptions",
          route: "/admin/subscriptions",
        },
        {
          icon: "mdi-widgets",
          text: "Gyms",
          route: "/admin/gyms",
        },
      ],
      //   items: ["Basic", "Premium"],
    };
  },

  async created() {},
  methods: {
    async addGym() {
      try {
        await axios.post("http://localhost:8081/sala", {
          name: this.name,
          adress: this.adress,
        });
        this.$router.push({ name: "gymsAdmin" });
      } catch (e) {
        alert("Eroare adaugare Gym!");
      }
    },
  },
};
</script>


