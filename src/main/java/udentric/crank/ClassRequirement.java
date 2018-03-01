/*
 * Copyright (c) 2018 Alex Dubov <oakad@yahoo.com>
 *
 * This file is made available under the GNU General Public License
 * version 3 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package udentric.crank;

class ClassRequirement implements Requirement {
	ClassRequirement(Class<?> cls_) {
		cls = cls_;
	}

	Class<?> requiredClass() {
		return cls;
	}

	void fullfill(Offering off) {
		fullfillment = off;
	}

	@Override
	public Unit makeUnit() throws ReflectiveOperationException {
		if (cls.isArray() || null == cls.getSuperclass())
			return null;

		return new Unit(cls);
	}

	@Override
	public Unit getReferred() {
		if (fullfillment == null)
			return null;

		if (fullfillment instanceof ClassOffering) {
			return ((ClassOffering)fullfillment).unit;
		} else if (fullfillment instanceof AbstractClassOffering) {
			return ((AbstractClassOffering)fullfillment).unit;
		} else {
			return null;
		}
	}

	private final Class<?> cls;
	private Offering fullfillment;
}
