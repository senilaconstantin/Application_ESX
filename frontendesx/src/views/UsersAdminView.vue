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
          <div id="app">
            <v-app >
              <v-list subheader two-line>
                <v-subheader inset>Users</v-subheader>

                <v-list-item v-for="user in users" :key="user.id">
                  <v-list-item-avatar>
                    <v-icon class="grey lighten-1" dark> mdi-folder </v-icon>
                  </v-list-item-avatar>

                  <v-list-item-content>
                    <v-list-item-title
                      v-text="user.firstName"
                    ></v-list-item-title>

                    <!-- <v-list-item-title
                      v-text="user.lastName"
                    ></v-list-item-title> -->

                    <v-list-item-subtitle
                      v-text="user.mail"
                    ></v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-btn icon @click="update(user.id)">
                      <v-icon color="grey lighten-1">mdi-pencil</v-icon>
                    </v-btn>
                    <v-btn icon @click="dialog = true">
                      <v-icon color="grey lighten-1">mdi-delete</v-icon>
                    </v-btn>
                    <v-dialog v-model="dialog" max-width="290">
                      <v-card>
                        <v-card-title class="text-h5"> Delete </v-card-title>

                        <v-card-text>
                          Sigur vrei sa stergi userul?
                        </v-card-text>

                        <v-card-actions>
                          <v-spacer></v-spacer>

                          <v-btn
                            color="green darken-1"
                            text
                            @click="dialog = false"
                          >
                            Disagree
                          </v-btn>

                          <v-btn
                            color="green darken-1"
                            text
                            @click="deletee(user.id)"
                          >
                            Agree
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-list-item-action>
                </v-list-item>
              </v-list>
            </v-app>
          </div>
        </v-form>
      </v-col>
      <v-col>
        <v-btn color="green darken-1"  @click= "$router.push({ name: 'addUser' })"> Add User </v-btn>
      </v-col>
    </v-row>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "UsersAdminView",
  data() {
    return {
      users: [],
      lodUserId: window.localStorage.getItem("idAdmin"),
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
    };
  },

  async created() {
    this.getUsers();
  },
  methods: {
    async update(id) {
      window.localStorage.setItem("back", "users");
      window.localStorage.setItem("idU", id);
      this.$router.push({
        name: "updateAdmin",
        params: { id: id },
      });
    },
    async deletee(id) {
      this.dialog = false;
      try {
        await axios.delete("http://localhost:8081/users/" + id);
        this.getUsers();
      } catch (e) {
        alert("Ceva gresit!");
      }
    },
    async getUsers() {
      var result = await axios.get(
        "http://localhost:8081/users/getAll/" + this.lodUserId
      );
      this.users = result.data;
    },
  },
};
</script>


