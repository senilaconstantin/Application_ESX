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
        <!-- <v-list-item>
          <v-list-item-title>Foo</v-list-item-title>
        </v-list-item> -->
      </v-list-item-group>
    </v-navigation-drawer>
    <v-row>
      <v-col lg="8">
        <v-form ref="form" class="ml-6 mr-6">
          <v-text-field v-model="tip" label="Name Type" required></v-text-field>

          <v-text-field v-model="pret" label="Price" required></v-text-field>

          <v-btn color="success" class="mr-4" @click="updateSubscriptions">
            Update
          </v-btn>

          <v-btn
            color="success"
            class="mr-4"
            @click="$router.push({ name: 'subscriptions' })"
          >
            Back
          </v-btn>
        </v-form>
      </v-col>
      <v-col>
        <v-btn
          color="success"
          class="mr-4"
          @click="$router.push({ name: 'addGymAb' })"
        >
          Add Gym
        </v-btn>
        <v-btn
          color="success"
          class="mr-4"
          @click="$router.push({ name: 'deleteGymAb' })"
        >
          Delete Gym
        </v-btn>
      </v-col>
    </v-row>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "UpdateSubscriptions",
  data() {
    return {
      tip: "",
      pret: "",
      idAb: window.localStorage.getItem("idSubs"),
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
    };
  },

  async created() {
    try {
      // alert(this.$route.params.idAb);
      var result = await axios.get(
        "http://localhost:8081/abonament/" + this.idAb
      );
      this.tip = result.data.tipAbonament;
      this.pret = result.data.pret;
    } catch (e) {
      alert("Eroare la init!");
    }
  },
  methods: {
    async updateSubscriptions() {
      // alert(this.idAb+" "+this.tip+" "+this.pret);
      try {
        await axios.put("http://localhost:8081/abonament", {
          id: this.idAb, ///this.$route.params.idAb,
          tipAbonament: this.tip,
          pret: this.pret,
        });
        this.$router.push({ name: "subscriptions" });
      } catch (e) {
        alert("Eroare update!");
      }
    },
  },
};
</script>


